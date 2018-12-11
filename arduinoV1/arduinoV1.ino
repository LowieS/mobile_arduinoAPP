char c;
char Menus;
bool sub=false;
void setup()  
{  
  Serial.begin(9600);   
  pinMode(2,OUTPUT); 
  pinMode(3,OUTPUT);  
  
 
}  
  
void loop()  
{  
  
     
     if (Serial.available())  
     {  
       c = Serial.read();
      Serial.println(c);

      
       if(sub==true){

        Menus=c;
        sub = false;
      }
     
      if(c=='m'){
        sub=true;
      }
     

       if(c=='f'&&Menus=='0'){
        
        
        digitalWrite(3,HIGH);
        digitalWrite(2,LOW);
       }
       if(c=='t'&&Menus=='0'){
        
        
        digitalWrite(2,HIGH);
        digitalWrite(3,LOW);
       }
        
     
    } 
} 
