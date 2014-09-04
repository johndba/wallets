package com.coinomi.wallet.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinomi.wallet.R;
import com.coinomi.wallet.util.Fonts;
import com.google.bitcoin.core.Coin;

/**
 * @author Giannis Dzegoutanis
 */
public class Amount extends RelativeLayout{
    private final TextView amount;
    private final TextView symbol;

    boolean isBig = false;

    public Amount(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.amount, this, true);

        amount = (TextView) findViewById(R.id.amount);
        symbol = (TextView) findViewById(R.id.symbol);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Amount, 0, 0);
        try {
            isBig = a.getBoolean(R.styleable.Amount_show_big, false);
        } finally {
            a.recycle();
        }

        try {
            if (isBig) {
                amount.setTextAppearance(context, R.style.AmountBig);
            }
            Fonts.setTypeface(amount, isBig ? Fonts.Font.ROBOTO_LIGHT : Fonts.Font.ROBOTO_REGULAR);
            Fonts.setTypeface(symbol, Fonts.Font.ROBOTO_LIGHT);
        } catch (RuntimeException ignore) { }

    }

    public void setAmount(Coin amount) {
        this.amount.setText(amount.toPlainString());
    }

    public void setSymbol(String symbol) {
        this.symbol.setText(symbol);
    }




}
