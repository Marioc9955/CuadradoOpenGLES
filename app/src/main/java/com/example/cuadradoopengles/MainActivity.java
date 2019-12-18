package com.example.cuadradoopengles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGLSurfaceView = new CustomGLSurfaceView(this);
        setContentView(mGLSurfaceView);
    }
    class GLRenderer implements GLSurfaceView.Renderer {
        private  Cuadrado c;


        // Para rotar

        private float[] mRotationMatrix = new float[16];

        // Para la camara

        private  final  float[]  mMVPMatrix = new float[16];
        private  final  float[]  mProjectionMatrix = new float[16];
        private  final  float[]  mViewMatrix = new float[16];


        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
            c.draw();
        }

        @Override
        public void onSurfaceCreated(GL10 gl, javax.microedition.khronos.egl.EGLConfig config) {
            GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
            c = new Cuadrado();
        }

        @Override
        public void onSurfaceChanged(GL10 unused, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
            float ratio = (float) width / height;
            // Camara
            Matrix.frustumM(mProjectionMatrix,0,-ratio,ratio,-1,1,3,7);
        }


    }

    class CustomGLSurfaceView extends GLSurfaceView{
        private  final  GLRenderer mGLRenderer;

        public  CustomGLSurfaceView(Context context){
            super(context);
            setEGLContextClientVersion(2);
            mGLRenderer = new GLRenderer();
            setRenderer(mGLRenderer);
        }

    }
}
