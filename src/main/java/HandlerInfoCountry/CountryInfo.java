package HandlerInfoCountry;


public class CountryInfo implements Comparable {
        public String country;
        public  String capital;
        public CountryInfo(String country, String capital){
            this.capital = capital;
            this.country = country;
        }

        public CountryInfo(String info, TypeInfo typeInfo) {
            if(typeInfo == TypeInfo.CAPITAL)
                this.capital = info;
            else this.country = info;
        }

        public CountryInfo(){}
        public String getCapital() {
            return capital;
        }
        public String getCountry(){
            return country;
        }

        @Override
        public String toString() {
            return "HandlerInfoCountry.CountryInfo{" +"" +
                    "capital: '" + capital + '\'' +
                    "country: "+ country + '}';

        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }
        @Override
        public int compareTo(Object o) {
            try{
                if(!(o instanceof CountryInfo)) throw new Exception("Ошибка. Тип объекта не HandlerInfoCountry.CountryInfo!");

            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            return capital.compareTo(((CountryInfo)o).capital);
        }


    }
