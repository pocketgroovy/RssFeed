## Feature:
One of the most notable aspects of developing my RSS reader app was utilizing containers for all components, including MySQL. This approach ensures that the project can run seamlessly across various environments and offers excellent scalability.

## Challenges in Development:
### 1. Docker's Unique Networking:
   I implemented three different Docker containers for this project: the RSS Retriever, the RSS Subscriber (user interface), and the MySQL database. If the database were running directly on the host server (i.e., not containerized), the app could communicate with the database locally using localhost, avoiding the need for internet-based interactions. However, with containerization, it's like having three separate servers. Without a domain name or at least an IP address, these containers can't communicate with each other.

I was reluctant to use IP addresses due to the lack of guarantee that they would remain consistent upon container reinitialization. Fixing the IP addresses would undermine the flexibility and mobility benefits of Docker. Fortunately, Docker allows containers to communicate using their names, provided they are within the same custom network. This way, as long as the intended containers are in the same network, I can simply use their names for interactions, including with the database.

### 2. Network Security:
   To enhance security and prevent the network from being directly accessible from the internet, I incorporated Nginx as a proxy server. Nginx acts as an intermediary, forwarding user requests to retrieve data, thereby adding an additional layer of security.

Additionally, client requests are made over HTTPS. By having Nginx handle the HTTPS connections and forward requests to the internal services over localhost, I avoid the need for SSL certificates for the app components within the internal network. This approach saves me from the hassle of periodic certificate renewal and reduces the costs associated with maintaining SSL certificates for internal services.

