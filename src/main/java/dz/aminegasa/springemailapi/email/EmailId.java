package dz.aminegasa.springemailapi.email;

//this class for auto increment id
  class EmailId {
      private static  Integer id=1;
      static Integer autoIncrements(){
         return id++;
      }
}
