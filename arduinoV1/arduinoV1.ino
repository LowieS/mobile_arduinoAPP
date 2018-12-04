 float h = 0;
 float t = 0;

void setup() {
  Serial.begin(9600);
  }

void loop()
{  char c; 
if(Serial.available())  
  {  
   c = Serial.read();  
   if(c=='t')
   readSensor();
  }}
void readSensor() {
  h ++;
   t ++;
  
  float hic = "lala hic"
  Serial.print("Humidity: ");
  Serial.print(h);
  Serial.print(" %\t");
  Serial.print("Temperature: ");
  Serial.print(t);
  Serial.print(" *C ");
  Serial.print("Heat index: ");
  Serial.print(hic);
  Serial.print(" *C ");
}
