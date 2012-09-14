/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.manning.siia.trip.diary;

import static com.manning.siia.trip.diary.TextChanges.deletion;
import static com.manning.siia.trip.diary.TextChanges.insertion;

/**
 * @author Iwein Fuld
 */
public class EditableText {
    private String currentText;
    private int version;

    public EditableText(String s) {
        this.currentText = s;
    }

    public TextChange insert(String toInsert, int position) {
        TextChange change = insertion(version, toInsert, position);
        this.apply(change);
        return change;
    }

    public String getText() {
        return currentText;
    }

    public void apply(TextChange textChange) {
        if (textChange.getVersion() != this.version) {
            throw new RuntimeException("The version of this text (" + version + ") is not the same as the version to which this change applies (" + textChange.getVersion() + ").");
        }
        currentText = textChange.applyTo(currentText);
        version = version + 1;
    }

    public int getVersion() {
        return version;
    }

    public TextChange delete(int start, int end) {
        TextChange change = deletion(version, start, end);
        apply(change);
        return change;
    }
}
