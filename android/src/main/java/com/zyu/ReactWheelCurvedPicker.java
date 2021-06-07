package com.zyu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.SystemClock;
import android.util.AttributeSet;

import com.aigestudio.wheelpicker.IWheelPicker;
import com.aigestudio.wheelpicker.WheelPicker;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.List;

/**
 * @author <a href="mailto:lesliesam@hotmail.com"> Sam Yu </a>
 */
public class ReactWheelCurvedPicker extends WheelPicker implements IWheelPicker {
    private final EventDispatcher mEventDispatcher;
    private List<Integer> mValueData;
    private Integer mLineColor = Color.BLACK; // Default line color
    private boolean isLineGradient = false;    // By default line color is not a gradient
    private Integer mLinegradientFrom = Color.BLACK; // Default starting gradient color
    private Integer mLinegradientTo = Color.WHITE; // Default end gradient color
    protected Integer mState = 0;
    public ReactWheelCurvedPicker(ReactContext reactContext) {
        super(reactContext);
        mEventDispatcher = reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        this.setCyclic(true);
        this.setCurved(true);
        setOnWheelChangeListener(new OnWheelChangeListener() {
            @Override
            public void onWheelScrolled(int offset) {

            }

            @Override
            public void onWheelSelected(int position) {
                if (mValueData != null && position < mValueData.size()) {
                    mEventDispatcher.dispatchEvent(
                            new ItemSelectedEvent(getId(), mValueData.get(position)));
                }
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
                    mState= state;
            }
        });
    }

    public int getState() {
        return mState;
    }
    @Override
    public void setCyclic(boolean isCyclic) {
        super.setCyclic(isCyclic);
    }
    @Override
    public void setCurved(boolean isCurved){
        super.setCurved(isCurved);
    }
    //    @Override
//    protected void drawForeground(Canvas canvas) {
////        super.drawForeground(canvas);
//
////         Paint paint = new Paint();
//// //         paint.setColor(this.mLineColor); // changed this from WHITE to BLACK
////         if (this.isLineGradient) {
////             int colorFrom = this.mLinegradientFrom;
////             int colorTo = this.mLinegradientTo;
////             LinearGradient linearGradientShader = new LinearGradient(rectCurItem.left, rectCurItem.top, rectCurItem.right/2, rectCurItem.top, colorFrom, colorTo, Shader.TileMode.MIRROR);
////             paint.setShader(linearGradientShader);
////         }
////         canvas.drawLine(rectCurItem.left, rectCurItem.top, rectCurItem.right, rectCurItem.top, paint);
////         canvas.drawLine(rectCurItem.left, rectCurItem.bottom, rectCurItem.right, rectCurItem.bottom, paint);
//    }

    public void setLineColor(Integer color) {
//         this.mLineColor = color;
    }

    public void setLineGradientColorFrom (Integer color) {
        this.isLineGradient = true;
        this.mLinegradientFrom = color;
    }

    public void setLineGradientColorTo (Integer color) {
        this.isLineGradient = true;
        this.mLinegradientTo = color;
    }

    @Override
    public void setSelectedItemPosition(int index) {
        super.setSelectedItemPosition(index);
//        unitDeltaTotal = 0;
//		mHandler.post(this);
    }

    public void setValueData(List<Integer> data) {
        mValueData = data;
    }

}

class ItemSelectedEvent extends Event<ItemSelectedEvent> {

    public static final String EVENT_NAME = "wheelCurvedPickerPageSelected";

    private final int mValue;

    protected ItemSelectedEvent(int viewTag,  int value) {
        super(viewTag);
        mValue = value;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putInt("data", mValue);
        return eventData;
    }
}
