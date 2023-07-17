package pojo.restfullbooker;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTokenRequest {



        @JsonProperty("username")
        private String kullaniciAdi;
        @JsonProperty("password")
        private String sifre;

        public String getKullaniciAdi() {
            return kullaniciAdi;
        }

        public void setKullaniciAdi(String kullaniciAdi) {
            this.kullaniciAdi = kullaniciAdi;
        }

        public String getSifre() {
            return sifre;
        }

        public void setSifre(String sifre) {
            this.sifre = sifre;
        }


        @Override
        public String toString() {
            return "CreateTokenRequest{" +
                    "kullaniciAdi='" + kullaniciAdi + '\'' +
                    ", sifre='" + sifre + '\'' +
                    '}';
        }
    }

