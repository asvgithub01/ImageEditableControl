package com.gigigo.asv.imageedit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.shapes.ArcShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.view.View.OnTouchListener;

/**
 * Created by Alberto on 17/03/2015.
 */
public class EditableImageView extends ImageView implements OnTouchListener  {

    View DragView;

    private boolean inDrag;
    private boolean inMove;
    int xDragTouchOffset, yDragTouchOffset;
    int xDragImageOffset, yDragImageOffset;
    //    ImageView LeftTopArrow;
//    ImageView RigthBottomArrow;
    //RelativeLayout layParent;

    ArcShape resRBottom;


    public EditableImageView(Context context) {
        super(context);
        SetEvents();
        AddArrows();

    }

    private void AddArrows() {

//        LeftTopArrow =new ImageView(getContext());
//        LeftTopArrow.setImageResource(R.drawable.ic_uparrow);
//        RelativeLayout layParent = (RelativeLayout) this.getParent() ;
//        layParent.addView(LeftTopArrow);
    }

    private void SetEvents() {
//asv habria q desescuchar tb
        this.setOnTouchListener(this);
    }

//    private void SetFaderEvents() {
//
//        layParent = (RelativeLayout) DragView.getParent();
//
//        layParent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                final int action = event.getAction();
//                final int x = (int) event.getX();
//                final int y = (int) event.getY();
//                boolean result = false;
//                if (action == MotionEvent.ACTION_UP) {
//
//
//                    Toast.makeText(getContext(), "EVENTO CLICK, un down y despues un Up sin movimiento", Toast.LENGTH_LONG).show();
//
//                    //  DragView.setMinimumHeight(DragView.getHeight()+10);
//                    int xDiff = 0;
//                    int yDiff = 0;
//                    xDiff = xDragTouchOffset - x;
//                    yDiff = yDragTouchOffset - y;
//
//                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) DragView.getLayoutParams();
//                    params.height = DragView.getHeight() + xDiff;
//                    params.width = DragView.getWidth() + yDiff;
//                    DragView.setLayoutParams(params);
//
//                    DragView.onTouchEvent(event);
//
//
//                }
//
//
//                return false;
//            }
//        });
//
//    }

    public EditableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SetEvents();
        AddArrows();
    }

    public EditableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SetEvents();
        AddArrows();
    }


    @Override
    public boolean onTouch(View View, MotionEvent event) {

        DragView = View;
//        if (layParent == null)
//            SetFaderEvents();

//        if(LeftTopArrow==null) {
//            LeftTopArrow = new ImageView(getContext());
//            LeftTopArrow.setImageResource(R.drawable.ic_uparrow);
//            RelativeLayout layParent = (RelativeLayout) DragView.getParent();
//            RelativeLayout.LayoutParams rParams = (RelativeLayout.LayoutParams) DragView.getLayoutParams();
//
//            layParent.addView(LeftTopArrow,rParams);
//        }

        final int action = event.getAction();
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        boolean result = false;
        if (action == MotionEvent.ACTION_DOWN) {
            inDrag = true;
            xDragTouchOffset = x;
            yDragTouchOffset = y;
            result = true;
            inMove = false;
        } else if (action == MotionEvent.ACTION_MOVE && inDrag == true) {
            //setDragImagePosition(x, y);
            inMove = true;
            ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(DragView.getLayoutParams());
            int left = (int) event.getRawX() - (DragView.getWidth() / 2);
            int top = (int) event.getRawY() - (DragView.getHeight());
            marginParams.setMargins(left, top, 0, 0);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
            DragView.setLayoutParams(layoutParams);


            result = true;
        } else if (action == MotionEvent.ACTION_UP && inDrag == true) {

            if (inMove)
                Toast.makeText(this.getContext(), "Up despues de mover", Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(this.getContext(), "EVENTO CLICK, un down y despues un Up sin movimiento", Toast.LENGTH_LONG).show();

                //  DragView.setMinimumHeight(DragView.getHeight()+10);
                int xDiff = 30;
                int yDiff = 30;
                xDiff = xDragTouchOffset - x;
                yDiff = yDragTouchOffset - y;

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) DragView.getLayoutParams();
                params.height = DragView.getHeight() + xDiff;
                params.width = DragView.getWidth() + yDiff;
                DragView.setLayoutParams(params);

            }

            inMove = false;
            inDrag = false;
            result = true;
        }

        return result;

    }

    private void setDragImagePosition(int x, int y) {
        try {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) DragView
                    .getLayoutParams();

//ImageView imgV=(ImageView)DragView;

            xDragImageOffset = 0; //lp.leftMargin;
            yDragImageOffset = 0;//lp.topMargin;// DragView.getTop(); topmargin

            int xMargin = x - xDragImageOffset - xDragTouchOffset;
            int yMargin = y - yDragImageOffset - yDragTouchOffset;

            if (xMargin < 0) xMargin = xMargin + -1;
            if (yMargin < 0) yMargin = yMargin + -1;

            lp.setMargins(xMargin, yMargin, 0, 0);
            super.setLayoutParams(lp);

//            DragView.setTop(y-yDragTouchOffset);
//            DragView.setRight(x-xDragTouchOffset);


        } catch (Exception e) {
            Log.e("", e.toString());
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        //Llamamos al método de la clase base (EditText)
        super.onDraw(canvas);

        Paint  p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p1.setColor(Color.GREEN);
        p1.setStyle(Paint.Style.FILL);

        resRBottom = new ArcShape(30,30);

       // canvas.drawArc();

//        p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        p2.setColor(Color.WHITE);
//        p2.setTextSize(30)
float escala =getResources().getDisplayMetrics().density;
        //Dibujamos el fondo negro del contador
        //left, top, right, bottom
        canvas.drawRect(this.getWidth()-30*escala, 5*escala, this.getWidth()-5*escala, 20*escala,p1);
        canvas.drawRect(this.getHeight()-30*escala,
                5*escala,
                this.getHeight()-5*escala,
                20*escala,p1);

        //Dibujamos el número de caracteres sobre el contador
       // canvas.drawText("" + this.getText().toString().length(), this.getWidth()-28*escala, 17*escala, p2);

       //canvas.dr
    }


}
