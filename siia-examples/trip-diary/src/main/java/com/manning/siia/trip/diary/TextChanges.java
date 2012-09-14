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

/**
 * @author Iwein Fuld
 */
public class TextChanges {
    public static TextChange deletion(int version, int start, int end){
        return new DeletingTextChange(version, start, end);
    }

    public static TextChange insertion(int version, String toInsert, int position){
        return new InsertingTextChange(version, toInsert, position);
    }

    private abstract static class AbstractTextChange implements TextChange {
        private int version;

        public AbstractTextChange(int version) {
            this.version = version;
        }

        public int getVersion() {
            return version;
        }
    }

    private static class DeletingTextChange extends AbstractTextChange {
        private int end;
        private int start;

        public DeletingTextChange(int version, int start, int end) {
            super(version);
            this.start = start;
            this.end = end;
        }

        public String applyTo(String original) {
            return new StringBuilder(original.length() + start - end).append(original).delete(start, end).toString();
        }
    }

    private static class InsertingTextChange extends AbstractTextChange {
        private final String toInsert;
        private final int position;

        public InsertingTextChange(int version, String toInsert, int position) {
            super(version);
            this.toInsert = toInsert;
            this.position = position;
        }

        public String applyTo(String original) {
            StringBuilder builder = new StringBuilder(original.length() + toInsert.length());
            return builder.append(original.substring(0, position)).append(toInsert).append(original.substring(position)).toString();
        }
    }
}
