# BasicContainer
This is a Basic K8s Deployable SpringBoot App Deployment Demo

## **Project Overview: BasicContainer**

**BasicContainer** is a cloud-native demonstration of a high-performance Java 25 microservice, integrated into a complete **GitOps CI/CD pipeline**. The project showcases the transition from legacy infrastructure to a modern, containerized deployment using **Spring Boot 4.0.5**, **GitHub Actions**, **GHCR**, and **Argo CD**.

### **Core Technology Stack**

* **Language/Runtime:** Java 25 (LTS Candidate) on Oracle Linux 9\.  
* **Framework:** Spring Boot 4.0.5 (Bleeding Edge).  
* **Build Tool:** Gradle 8.x with specialized GitHub Actions integration.  
* **Containerization:** Docker with multi-tagging (SHA \+ Semantic Versioning).  
* **Orchestration:** Kubernetes (Minikube/VirtualBox) with Helm-based deployments.  
* **CD/GitOps:** Argo CD for automated state synchronization.

---

## **Architectural Setup & Workflow**

### 1\. **Continuous Integration (GitHub Actions)**

The pipeline is defined in .github/workflows/gradle.yml and performs the following:

* **JDK 25 Environment:** Utilizes the temurin distribution to build the project.  
* **Automated Testing:** Executes JUnit 5 POJO tests to ensure core logic integrity without the overhead of a full Spring Context, bypassing bytecode issues common in early JDK releases.  
* **Secure Build & Push:** Logs into **GitHub Container Registry (GHCR)** using repository secrets and builds a Docker image using the openjdk:25-oraclelinux9 base.  
* **Tagging Strategy:** Implements a dual-tagging approach:  
  * **Static/Release Tags:** Currently using 003 for immutable deployments.  
  * **Rolling Tags:** Maintaining latest for rapid development cycles.

### 2\. **Containerization (Dockerfile)**

The Dockerfile is optimized for the **Oracle Linux 9** ecosystem:

* **Base Image:** container-registry.oracle.com/java/openjdk:25-oraclelinux9.  
* **Artifact Alignment:** Specifically copies build/libs/BasicContainer-0.1.0.jar to ensure the container version matches the Gradle project version.  
* **Network:** Exposes port 8080 for standard Spring Boot web traffic.

### 3\. **Kubernetes Orchestration (Helm & Argo CD)**

The deployment is managed via a custom Helm chart located in /charts/spring-app:

* **Resource Management:** Defines a Deployment with 1 replica and a NodePort service for local access.  
* **Image Pull Strategy:** Configured with imagePullPolicy: Always and imagePullSecrets (github-pull-secret) to guarantee that the Minikube node always pulls the freshest bytes from GHCR, even when reusing the latest tag.  
* **Access:** The application is exposed via NodePort 30080 for easy verification on the host machine.

---

## **Key Solved Challenges (Interview Talking Points)**

* **Java 25 Stability:** Successfully identified and bypassed ByteBuddy and Mockito incompatibilities with JDK 25 by implementing a "Brute Force" POJO testing strategy. This ensured CI gates remained green while using bleeding-edge runtimes.  
* **Registry Authentication:** Implemented secure image pulls from a private GHCR repository using Kubernetes secrets, moving beyond local-only image storage.  
* **Cache Invalidation:** Resolved the "Latest Tag Trap" by enforcing an Always pull policy, ensuring that Argo CD's automated sync accurately reflects the most recent GitHub Actions build.

---

### **Final Project Stats**

* **Current App Version:** 0.1.0.  
* **Current Chart Version:** 0.1.1.  
* **Verified Digest:** sha256:007be3c1dd141bd26fe1f87947f7788cb2fbc99b788bd5d76d8992401ef465b1.

------
### SEE ALSO
1. Kubernetes Setup (..Coming Soon)
2. ArgoCD (..Coming Soon)

