package com.zql.comm.netbean.response;

import java.util.List;

public class CapsulesResponse {

    /**
     * list : [{"pk":6,"fields":{"capsule_date":"","capsule_image":"","capsule_person":"","capsule_create_time":"2020-01-13T01:28:47.909Z","capsule_type":"生活","capsule_id":"zqlswa","capsule_content":"对对对","capsule_location":"","capsule_time":""},"model":"memorycapsuleservice.capsule"},{"pk":1,"fields":{"capsule_date":"","capsule_image":"","capsule_person":"","capsule_create_time":"2020-01-08T14:14:41.635Z","capsule_type":"工作","capsule_id":"zqlswa","capsule_content":"001","capsule_location":"","capsule_time":""},"model":"memorycapsuleservice.capsule"}]
     * error_name : 207
     * msg : success
     */

    private int error_name;
    private String msg;
    private List<ListBean> list;

    public int getError_name() {
        return error_name;
    }

    public void setError_name(int error_name) {
        this.error_name = error_name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * pk : 6
         * fields : {"capsule_date":"","capsule_image":"","capsule_person":"","capsule_create_time":"2020-01-13T01:28:47.909Z","capsule_type":"生活","capsule_id":"zqlswa","capsule_content":"对对对","capsule_location":"","capsule_time":""}
         * model : memorycapsuleservice.capsule
         */

        private int pk;
        private FieldsBean fields;
        private String model;

        public int getPk() {
            return pk;
        }

        public void setPk(int pk) {
            this.pk = pk;
        }

        public FieldsBean getFields() {
            return fields;
        }

        public void setFields(FieldsBean fields) {
            this.fields = fields;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public static class FieldsBean {
            /**
             * capsule_date :
             * capsule_image :
             * capsule_person :
             * capsule_create_time : 2020-01-13T01:28:47.909Z
             * capsule_type : 生活
             * capsule_id : zqlswa
             * capsule_content : 对对对
             * capsule_location :
             * capsule_time :
             */

            private String capsule_date;
            private String capsule_image;
            private String capsule_person;
            private String capsule_create_time;
            private String capsule_type;
            private String capsule_id;
            private String capsule_content;
            private String capsule_location;
            private String capsule_time;

            public String getCapsule_date() {
                return capsule_date;
            }

            public void setCapsule_date(String capsule_date) {
                this.capsule_date = capsule_date;
            }

            public String getCapsule_image() {
                return capsule_image;
            }

            public void setCapsule_image(String capsule_image) {
                this.capsule_image = capsule_image;
            }

            public String getCapsule_person() {
                return capsule_person;
            }

            public void setCapsule_person(String capsule_person) {
                this.capsule_person = capsule_person;
            }

            public String getCapsule_create_time() {
                return capsule_create_time;
            }

            public void setCapsule_create_time(String capsule_create_time) {
                this.capsule_create_time = capsule_create_time;
            }

            public String getCapsule_type() {
                return capsule_type;
            }

            public void setCapsule_type(String capsule_type) {
                this.capsule_type = capsule_type;
            }

            public String getCapsule_id() {
                return capsule_id;
            }

            public void setCapsule_id(String capsule_id) {
                this.capsule_id = capsule_id;
            }

            public String getCapsule_content() {
                return capsule_content;
            }

            public void setCapsule_content(String capsule_content) {
                this.capsule_content = capsule_content;
            }

            public String getCapsule_location() {
                return capsule_location;
            }

            public void setCapsule_location(String capsule_location) {
                this.capsule_location = capsule_location;
            }

            public String getCapsule_time() {
                return capsule_time;
            }

            public void setCapsule_time(String capsule_time) {
                this.capsule_time = capsule_time;
            }

            @Override
            public String toString() {
                return "FieldsBean{" +
                        "capsule_date='" + capsule_date + '\'' +
                        ", capsule_image='" + capsule_image + '\'' +
                        ", capsule_person='" + capsule_person + '\'' +
                        ", capsule_create_time='" + capsule_create_time + '\'' +
                        ", capsule_type='" + capsule_type + '\'' +
                        ", capsule_id='" + capsule_id + '\'' +
                        ", capsule_content='" + capsule_content + '\'' +
                        ", capsule_location='" + capsule_location + '\'' +
                        ", capsule_time='" + capsule_time + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "pk=" + pk +
                    ", fields=" + fields.toString() +
                    ", model='" + model + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CapsulesResponse{" +
                "error_name=" + error_name +
                ", msg='" + msg + '\'' +
                ", list=" + list.toString() +
                '}';
    }
}
