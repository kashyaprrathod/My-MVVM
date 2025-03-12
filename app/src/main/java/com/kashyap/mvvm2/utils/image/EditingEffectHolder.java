package com.kashyap.mvvm2.utils.image;

import com.kashyap.mvvm2.utils.image.model.EffectBase;
import com.kashyap.mvvm2.utils.image.model.ModelBorder;
import com.kashyap.mvvm2.utils.image.model.ModelDottedBorder;
import com.kashyap.mvvm2.utils.image.model.ModelRings;

public class EditingEffectHolder {

    public interface TypeOfEditing {
        int border = 1;
        int dotted = 2;
        int ring = 3;
        int none = 0;
    }

    int currentEditing = TypeOfEditing.border;

    ModelBorder borderEditor;
    ModelDottedBorder dottedBorderEditor;
    ModelRings ringBorderEditor;

    public EditingEffectHolder() {
        borderEditor = new ModelBorder();
        dottedBorderEditor = new ModelDottedBorder();
        ringBorderEditor = new ModelRings();
    }

    public void setCurrentEditing(int editing) {
        this.currentEditing = editing;
    }


    public EffectBase getModelAccordingtoCurrentEffect() {
        if (currentEditing == TypeOfEditing.border) {
            return borderEditor;
        } else if (currentEditing == TypeOfEditing.dotted) {
            return dottedBorderEditor;
        } else if (currentEditing == TypeOfEditing.ring) {
            return ringBorderEditor;
        } else {
            return null;
        }
    }


}
