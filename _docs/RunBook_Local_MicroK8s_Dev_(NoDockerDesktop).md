## **RunBook: Local K8s Development (No Docker Desktop)**

**Environment:** Windows 10/11 | Minikube (VirtualBox Driver) | Gradle | Java 25/26

### **1\. Daily Environment Initialization**

Because Minikube and its Docker daemon are not "native" to Windows, you must link your shell every time you open a new terminal.

**Start the Cluster:**  
DOS  
minikube start \--driver=virtualbox

* 

**Link the Docker CLI:** (Crucial: This tells your docker commands to talk to Minikube, not Windows)  
DOS  
Example: @FOR /f "tokens=\*" %i IN ('minikube \-p minikube docker-env \--shell cmd') DO @%i

    @FOR /F "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i

* 

### **2\. The Build & Deploy Loop**

Follow these steps whenever you change your Java code or build.gradle.

#### **Step A: Compile the Artifact**

Use the Gradle wrapper to ensure the JAR is built consistently.

DOS  
```./gradlew clean build```

#### **Step B: Build the Image**

Since your terminal is linked to Minikube (Step 1), this image stays inside the cluster.

DOS  
``` docker build -t hello-spring:0.0.1 . ```


#### **Step C: Update Kubernetes**

If you changed the deployment.yaml, apply it. If only the code changed, restart the pods to pull the new image.

DOS    
```
minikube kubectl \-- apply \-f deployment.yaml  
minikube kubectl \-- rollout restart deployment spring-hello-world
```

### **3\. Traffic & Debugging**

**Access the App:** Find the IP and Port assigned by the NodePort:  
DOS  
minikube service spring-service \--url

* 

**Live Tail Logs:**  
DOS  
minikube kubectl \-- logs \-f \-l app=spring-hello

* 

**Interactive Shell (SSH into Pod):**  
DOS  
minikube kubectl \-- exec \-it \<POD\_NAME\> \-- /bin/bash

* 

---

### **4\. Common Troubleshooting "Gotchas"**

| Issue | Symptom | Fix |
| :---- | :---- | :---- |
| **Image Not Found** | ErrImageNeverPull | Ensure imagePullPolicy: Never is in your YAML and you ran the @FOR link command before building. |
| **Wrong Java Version** | UnsupportedClassVersionError | Ensure your Dockerfile FROM matches your Gradle targetCompatibility. |
| **Minikube IP Change** | Connection Refused | VirtualBox IPs can shift. Run minikube ip to verify your target address. |

---

### **5\. Cleanup**

When you're done for the day, save your system resources:

DOS  
minikube stop

Would you like me to help you script these steps into a single .bat file so you can run the whole "Build-Tag-Deploy" sequence with one command?

