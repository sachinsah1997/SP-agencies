package com.example.sachinsah.sp_project;


    public class Globals{
        private static Globals instance;

        // Global variable
        private String data;

        private String type;

        private String product;

        // Restrict the constructor from being instantiated
        private Globals(){}


        public void setData(String d){
            this.data=d;
        }
        public String getData(){
            return data;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public static synchronized Globals getInstance(){
            if(instance==null){
                instance=new Globals();
            }
            return instance;
        }

    }

