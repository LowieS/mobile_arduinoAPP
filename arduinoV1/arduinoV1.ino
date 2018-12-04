 float h = 5;
 float t = 6;

void setup() {
  Serial.begin(9600);
  }

void loop()
{  
   char c;
if(Serial.available())
  {
   c = Serial.read();
   if(c=='t')
   readSensor();
  }
 
 }
void readSensor() {
  
  h++;
  t++;
  float hic = 66;
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
