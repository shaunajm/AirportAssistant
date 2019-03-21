package com.example.shaum.airportassistant;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;


public class ARCamera extends AppCompatActivity {
    private static final String TAG = ARCamera.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;

    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    private ModelRenderable secondaryModel;
    public Button btComplete;
    public Button btQuit;
    public AnchorNode baseNode;

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    // CompletableFuture requires api level 24
    // FutureReturnValueIgnored is not valid
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.activity_arcamera);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

        // When you build a Renderable, Sceneform loads its resources in the background while returning
        // a CompletableFuture. Call thenAccept(), handle(), or check isDone() before calling get().
        ModelRenderable.builder()
                .setSource(this, Uri.parse("redBall.sfb"))
                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast toast =
                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return null;
                        });

        ModelRenderable.builder()
                .setSource(this, Uri.parse("blueBall.sfb"))
                .build()
                .thenAccept(renderable -> secondaryModel = renderable)
                .exceptionally(
                        throwable -> {
                            Toast toast =
                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return null;
                        });

        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (modelRenderable == null) {
                        return;
                    }

                    // Create the Anchor.
                    Anchor anchor = hitResult.createAnchor();

                    if (baseNode == null) {
                        baseNode = new AnchorNode(anchor);
                        baseNode.setParent(arFragment.getArSceneView().getScene());
                        TransformableNode model = new TransformableNode(arFragment.getTransformationSystem());
                        model.setParent(baseNode);
                        model.setRenderable(modelRenderable);
                        model.select();
                    } else {
                        AnchorNode node = new AnchorNode(anchor);
                        node.setParent(arFragment.getArSceneView().getScene());
                        TransformableNode model = new TransformableNode(arFragment.getTransformationSystem());
                        model.setParent(node);
                        model.setRenderable(secondaryModel);
                        model.select();

                        // CALL FUNCTION THAT DRAWS LINE
                        final Vector3 diff = Vector3.subtract(baseNode.getWorldPosition(), node.getWorldPosition())
                                                    .normalized();
                        final Quaternion rotation = Quaternion.lookRotation(diff, Vector3.up());
                        MaterialFactory.makeOpaqueWithColor(getApplicationContext(), new Color(0, 255, 244))
                                .thenAccept(material -> {
                                    ModelRenderable mr = ShapeFactory.makeCube(
                                            new Vector3(.01f, .01f, diff.length()),
                                            Vector3.zero(), material);
                                    Log.d("lineBetweenPoints", "distance: " + diff.length());
                                    Node n = new Node();
                                    n.setParent(node);
                                    n.setRenderable(mr);
                                    n.setWorldPosition(Vector3.add(baseNode.getWorldPosition(), node.getWorldPosition()).scaled(.5f));
                                    n.setWorldRotation(rotation);
                                });
                    }
                });

        btComplete = (Button) findViewById(R.id.btCompleteScan);
        btComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARCamera.this, ARResults.class);
                startActivity(i);
                finish();
            }

        });

        btQuit = (Button) findViewById(R.id.btQuitScan);
        btQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARCamera.this, ARStart.class);
                startActivity(i);
                finish();
            }
        });
    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }
}