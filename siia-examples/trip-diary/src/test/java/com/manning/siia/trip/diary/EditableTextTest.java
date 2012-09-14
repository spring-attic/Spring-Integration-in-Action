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

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static com.manning.siia.trip.diary.TextChanges.*;

/**
 * @author Iwein Fuld
 */
public class EditableTextTest {
    private EditableText text;

    @Before
    public void createBaseline() {
        text = new EditableText("This is the baseline.");
    }

    @Test
    public void insert() {
        TextChange change = text.insert("edited ", 12);
        assertThat(change.getVersion(), is(0));
        assertThat(text.getText(), is("This is the edited baseline."));
        assertThat(text.getVersion(), is(1));
    }

    @Test
    public void delete() {
        text.delete(0, 5);
        assertThat(text.getText(), is("is the baseline."));
    }

    @Test
    public void shouldApplyChangeOnValidVersion() {
        text.apply(insertion(0, "edited ", 12));
        assertThat(text.getText(), is("This is the edited baseline."));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotApplyChangeToOlderVersion() {
        text.apply(insertion(1, "edited ", 12));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotApplyChangeToNewerVersion() {
        text.insert("Foo", 5);
        text.apply(insertion(0, "edited ", 12));
    }
}
