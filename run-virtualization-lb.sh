export PORT=8088
export API_URLS=http://localhost:8089,http://localhost:8089,http://localhost:8089

java -cp "virtualization-lb/target/classes:virtualization-lb/target/dependency/*" co.edu.escuelaing.lb.SparkWebServer