package com.goys.android.app.widgets;

import java.util.Hashtable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.goys.android.app.GOYSApplication;
import com.goys.android.app.R;
import com.goys.android.app.util.AppConstants;

public class TypefaceCache {
	protected static final int VARIATION_NORMAL = 0;
	protected static final int VARIATION_LIGHT = 1;

	private static final Hashtable<String, Typeface> mTypefaceCache = new Hashtable<String, Typeface>();

	protected static Typeface getTypeface(Context context) {
		return getTypeface(context, Typeface.NORMAL, VARIATION_NORMAL);
	}

	protected static Typeface getTypeface(Context context, int fontStyle,
			int variation) {
		if (context == null)
			return null;

		// note that the "light" variation doesn't support bold or bold-italic
		final String typefaceName;
		/*
		 * switch (fontStyle) { case Typeface.BOLD: typefaceName =
		 * "OpenSans-Bold.ttf"; break; case Typeface.ITALIC: typefaceName =
		 * (variation == VARIATION_LIGHT ? "OpenSans-LightItalic.ttf" :
		 * "OpenSans-Italic.ttf"); break; case Typeface.BOLD_ITALIC:
		 * typefaceName = "OpenSans-BoldItalic.ttf"; break; default:
		 * typefaceName = (variation == VARIATION_LIGHT ? "OpenSans-Light.ttf" :
		 * "OpenSans-Regular.ttf"); break; }
		 */

		if (!GOYSApplication.getInstance().isEnglishLanguage()) {
//		if(AppConstants.langType == 1){
			typefaceName = "adobe_arabic_regular.otf";
		} else {
			typefaceName = (variation == VARIATION_LIGHT ? "roboto_regular.ttf"
					: "roboto_medium.ttf");
		}

		if (!mTypefaceCache.containsKey(typefaceName)) {
			Typeface typeface = Typeface.createFromAsset(context
					.getApplicationContext().getAssets(), "fonts/"
					+ typefaceName);
			if (typeface != null) {
				mTypefaceCache.put(typefaceName, typeface);
			}
		}

		return mTypefaceCache.get(typefaceName);
	}

	/*
	 * sets the typeface for a TextView (or TextView descendant such as EditText
	 * or Button) based on the passed attributes, defaults to normal typeface
	 */
	protected static void setCustomTypeface(Context context, TextView view,
			AttributeSet attrs) {
		if (context == null || view == null)
			return;

		// skip at design-time
		if (view.isInEditMode())
			return;

		// read custom fontVariation from attributes, default to normal
		int variation = TypefaceCache.VARIATION_NORMAL;
		if (attrs != null) {
			TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
					R.styleable.GOYSTypeface, 0, 0);

			if (a != null) {
				try {
					variation = a.getInteger(
							R.styleable.GOYSTypeface_fontVariation,
							TypefaceCache.VARIATION_NORMAL);
				} finally {
					a.recycle();
				}
			}
		}

		// determine the font style from the existing typeface
		final int fontStyle;
		if (view.getTypeface() != null) {
			boolean isBold = view.getTypeface().isBold();
			boolean isItalic = view.getTypeface().isItalic();
			if (isBold && isItalic) {
				fontStyle = Typeface.BOLD_ITALIC;
			} else if (isBold) {
				fontStyle = Typeface.BOLD;
			} else if (isItalic) {
				fontStyle = Typeface.ITALIC;
			} else {
				fontStyle = Typeface.NORMAL;
			}
		} else {
			fontStyle = Typeface.NORMAL;
		}

		Typeface typeface = getTypeface(context, fontStyle, variation);
		if (typeface != null) {
			view.setTypeface(typeface);
		}
	}
}
