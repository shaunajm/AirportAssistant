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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;


public class ARCamera extends AppCompatActivity {
    private static final String TAG = ARCamera.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private ModelRenderable modelRenderable;
    private ModelRenderable secondaryModel;
    public AnchorNode baseNode;
    public int n = 0;
    public ArrayList<Float> distlist = new ArrayList<Float>();
    public ArrayList<AnchorNode> nodes = new ArrayList<AnchorNode>();
    public Button btQuit;
    public Button btComplete;
    public Button btClearPoints;

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference mUserRef = FirebaseDatabase.getInstance().getReference("users");
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.activity_arcamera);
        ArFragment arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (modelRenderable == null) {
                        return;
                    }

                    //Anchor is where the user taps on the screen
                    Anchor anchor = hitResult.createAnchor();

                    //Checks if a base node has already been placed
                    if (baseNode == null) {
                        //If no base node, creates a model for the anchor and places it on the plane
                        baseNode = new AnchorNode(anchor);
                        baseNode.setParent(arFragment.getArSceneView().getScene());
                        TransformableNode model = new TransformableNode(arFragment.getTransformationSystem());
                        model.setParent(baseNode);
                        model.setRenderable(modelRenderable);
                        model.select();
                        n++;
                    } else if (n < 4) {
                        //If a base node has been placed and we havent reached the max amount of nodes,
                        //Create a model and place it on the plane
                        AnchorNode node = new AnchorNode(anchor);
                        node.setParent(arFragment.getArSceneView().getScene());
                        nodes.add(node);
                        TransformableNode model = new TransformableNode(arFragment.getTransformationSystem());
                        model.setParent(node);
                        model.setRenderable(secondaryModel);
                        model.select();

                        //Get the vector distance between new node and base node
                        final Vector3 diff = Vector3.subtract(baseNode.getWorldPosition(), node.getWorldPosition());
                        final Quaternion rotation = Quaternion.lookRotation(diff, Vector3.up());
                        //Create the line between these two nodes and assign its colour
                        MaterialFactory.makeOpaqueWithColor(getApplicationContext(), new Color(0, 255, 244))
                                .thenAccept(material -> {
                                    //Make the line shape
                                    ModelRenderable mr = ShapeFactory.makeCube(
                                            new Vector3(.01f, .01f, diff.length()),
                                            Vector3.zero(), material);
                                    //Get the distance between the two nodes and convert this to cm
                                    Float distance = diff.length();
                                    Float distcm = distance * 100;
                                    //Add this value to the list of distances
                                    distlist.add(distcm);
                                    Log.d("lineBetweenPoints", "distance: " + diff.length());
                                    Log.d("distlist", "distanceList: " + distlist);
                                    //Place the line on the plane
                                    Node n = new Node();
                                    n.setParent(node);
                                    n.setRenderable(mr);
                                    n.setWorldPosition(Vector3.add(baseNode.getWorldPosition(), node.getWorldPosition()).scaled(.5f));
                                    n.setWorldRotation(rotation);
                                });
                        n++;
                    }
                    //Sort distlist to allow it to be checked against the airlines ordered dimensions
                    Collections.sort(distlist);
                    Log.d("sorteddistlist", "sorteddistanceList: " + distlist);

                    FirebaseUser user = mAuth.getCurrentUser();
                    mUserRef.child(user.getUid()).child("handluggage").setValue(distlist);
                });

        //Creates the base node's model
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

        //Creates the other node's models
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

        btClearPoints = (Button) findViewById(R.id.btClearPoints);
        btClearPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n > 0){
                    resetAR();
                }
            }
        });
    }

    //resets the AR screen by deleting the nodes and clearing the distance list
    public void resetAR() {
        baseNode.setParent(null);
        baseNode = null;
        for (AnchorNode node : nodes) {
            node.setParent(null);
        }
        distlist = new ArrayList<Float>();
        n = 0;
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
