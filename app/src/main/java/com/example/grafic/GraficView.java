package com.example.grafic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraficView extends View {

    List<Film> list;
    List<Film> lromantic=new ArrayList<>();
    List<Film> lsf=new ArrayList<>();
    List<Film> lcomedie=new ArrayList<>();

    Paint paint;
    Random random;
    int c1, c2, c3;

    public GraficView(Context context, List<Film> data) {
        super(context);
        this.list = data;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(7);
        random = new Random();

        // Set a random color
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        c1 = Color.argb(100, r, g, b);

        int r2 = random.nextInt(255);
        int g2 = random.nextInt(255);
        int b2 = random.nextInt(255);

        c2 = Color.argb(100, r2, g2, b2);

        int r3 = random.nextInt(255);
        int g3 = random.nextInt(255);
        int b3 = random.nextInt(255);

        c3 = Color.argb(100, r3, g3, b3);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(list == null || list.size() == 0)
            return;

        //float padding = 0.1f * Math.min(getHeight(), getWidth());
        float padding=5;

        float width = getWidth() - 2 * padding;
        float height = getHeight() - 2 * padding;

        float pointSpace = width/(list.size()+1);

        // Elem max
        float maxEl = 100;

        for(int i = 0;i<list.size();i++){
            if(list.get(i).getGen().equals(Gen.ROMANTIC)) lromantic.add(list.get(i));
            else if(list.get(i).getGen().equals(Gen.SF)) lsf.add(list.get(i));
            else if(list.get(i).getGen().equals(Gen.COMEDIE)) lcomedie.add(list.get(i));
        }

        paint.setTextSize(60);
        paint.setStrokeWidth(7);
        // Punctele
        for(int i = 0;i<lromantic.size();i++){
            // Draw line
            paint.setColor(c1);
            if(i >= 1){
                float x1 = padding + i*pointSpace;;
                float y1 = padding + height - (lromantic.get(i - 1).getPret()/(float)maxEl)*height;
                float x2 = padding + (i+1)*pointSpace;
                float y2 = padding + height - (lromantic.get(i).getPret()/(float)maxEl)*height;

                canvas.drawLine(x1, y1, x2, y2, paint);
            }

            // Draw point
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(20);

            float x = padding + (i+1)*pointSpace;
            float y = padding + height - (lromantic.get(i).getPret()/(float)maxEl)*height;
            canvas.drawPoint(x, y, paint);

            // Draw text
            canvas.drawText(String.valueOf(lromantic.get(i).getPret()), x - 30, y - 30, paint);
        }
        padding+=10;
        for(int i = 0;i<lcomedie.size();i++){
            // Draw line
            paint.setColor(c2);
            if(i >= 1){
                float x1 = padding + (i)*pointSpace;;
                float y1 = padding + height - (lcomedie.get(i - 1).getPret()/(float)maxEl)*height;
                float x2 = padding + (i+1)*pointSpace;
                float y2 = padding + height - (lcomedie.get(i).getPret()/(float)maxEl)*height;

                canvas.drawLine(x1, y1, x2, y2, paint);
            }

            // Draw point
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(20);

            float x = padding + (i+1)*pointSpace;
            float y = padding + height - (lcomedie.get(i).getPret()/(float)maxEl)*height;
            canvas.drawPoint(x, y, paint);

            // Draw text
            canvas.drawText(String.valueOf(lcomedie.get(i).getPret()), x - 30, y - 30, paint);
        }
        padding+=10;
        for(int i = 0;i<lsf.size();i++){
            // Draw line
            paint.setColor(c3);

            if(i >= 1){
                float x1 = padding + (i)*pointSpace;;
                float y1 = padding + height - (lsf.get(i - 1).getPret()/(float)maxEl)*height;
                float x2 = padding + (i+1)*pointSpace;
                float y2 = padding + height - (lsf.get(i).getPret()/(float)maxEl)*height;

                canvas.drawLine(x1, y1, x2, y2, paint);
            }

            // Draw point
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(20);

            float x = padding + (i+1)*pointSpace;
            float y = padding + height - (lsf.get(i).getPret()/(float)maxEl)*height;
            canvas.drawPoint(x, y, paint);

            // Draw text
            canvas.drawText(String.valueOf(lsf.get(i).getPret()), x - 30, y - 30, paint);
        }
        // Draw legenda
        paint.setTextSize(30);
        paint.setColor(c1);
        float x1 = width -200;
        float y1 = height -200;
        canvas.drawRect(x1,y1, x1+90, y1-90, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText("Romantic", x1 + 91, y1-20, paint);

        paint.setColor(c2);
        x1 = width -200;
        y1 = height -300;
        canvas.drawRect(x1,y1, x1+90, y1-90, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText("Comedie", x1 + 91, y1-20, paint);

        paint.setColor(c3);
        x1 = width -200;
        y1 = height -400;
        canvas.drawRect(x1,y1, x1+90, y1-90, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText("SF", x1 + 91, y1-20, paint);
    }
}

