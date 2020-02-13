package com.faisal.experiment.planegames;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.View;

import java.util.Random;


public class PlaneAnimation extends View {

    public static final int UPDATE_MILLIS = 30;
    private Bitmap background;
    //plane
    private Bitmap plane[] = new Bitmap[15];
    // tank
    private Bitmap tank;

    // plane position
    private int planeX, planeY;

    private int planeWidth, planeHeight;

    // device width
    private int devWidth;

    // device height
    private int devHeight;

    // ReactAngle
    Rect rect;

    // plane frame index
    int planeFrameIndex = 0;

    private Random random;

    // plane velocity
    int planeVelocity = 22;

    // we need handler to create runnable object
    private Handler handler;

    private Runnable runnable;

    private int tankWidth;
    private int tankHeight;

    public PlaneAnimation(Context context) {
        super(context);
        initialView();
        initialPlaneAsixs();
        getDeviceDimension();

        random = new Random();
        handler = new Handler();
        planeInitialXPoint();
        planeInitialYPoint();


    }

    private void planeInitialYPoint() {
        planeY = random.nextInt(100);
    }

    private void planeInitialXPoint() {
        planeX = devWidth + random.nextInt(200);
    }

    private void getDeviceDimension() {
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();

        Point size = new Point();

        display.getSize(size);

        getDeviceWidth(size);
        getDeviceHeight(size);


        /**
         * left  device starting point X
         *  top device starting point Y
         */
        rect = new Rect(0, 0, devWidth, devHeight);

        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        planeWidth = plane[0].getWidth();
        planeHeight = plane[0].getHeight();
        getTankWidth();
        getTankHeight();

    }
    private  int getTank_X_Position(){
        return devWidth/2-tankWidth/2;
    }
    private  int getTank_Y_Position(int canvasHeight){
        return canvasHeight-tankHeight;
    }

    private void getDeviceWidth(Point size) {
        devWidth = size.x;
    }

    private void getDeviceHeight(Point size) {
        devHeight = size.y;
    }

    private void getTankWidth() {
        tankWidth = tank.getWidth();
    }

    private void getTankHeight() {
        tankHeight = tank.getHeight();
    }
    private void initialPlaneAsixs() {
        planeX = 0;
        planeY = 0;
    }

    private void initialView() {
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        tank = BitmapFactory.decodeResource(getResources(), R.drawable.tank);

        plane[0] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_1);
        plane[1] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_2);
        plane[2] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_3);
        plane[3] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_4);
        plane[4] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_5);
        plane[5] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_6);
        plane[6] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_7);
        plane[7] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_8);
        plane[8] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_9);
        plane[9] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_10);
        plane[10] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_11);
        plane[11] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_12);
        plane[12] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_13);
        plane[13] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_14);
        plane[14] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // show background
        // canvas.drawBitmap(background, 0, 0, null);

        // show background with full scale
        canvas.drawBitmap(background, null, rect, null);

        planeFrameIndex++;
        if (planeFrameIndex == 15) {
            planeFrameIndex = 0;
        }
        movePlane();


        // jodi plane aste aste dan theke bame jete jete
        // screen er bahire chole jai tahole ki korbe
        if (planeX < -planeWidth) {
            planeInitialXPoint();
            planeInitialYPoint();

            // randomize the plane speed
            planeVelocity = 5 + random.nextInt(15);
        }
        // draw plane
        canvas.drawBitmap(plane[planeFrameIndex], planeX, planeY, null);

        canvas.drawBitmap(tank,getTank_X_Position(),getTank_Y_Position(canvas.getHeight()),null);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    private void movePlane() {
        planeX -= planeVelocity;
    }
}
