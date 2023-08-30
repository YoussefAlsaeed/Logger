# Log Management Component Installation Guide

The Log Management Component, developed using Spring Boot, serves as a critical module for receiving, validating, and effectively managing log data. This component seamlessly integrates with both POST API requests and Kafka channels as inputs to the module, and outputs to log files and Kafka topics along with ELK integration.

## Installation Steps

### Prerequisites

Ensure you have Docker installed and configured on your system. If you don't have Docker installed, you can download and install it from [Docker: Accelerated Container Application Development](https://www.docker.com/products/container-development).

### Download and Extract

1. Download the `download.zip` file from the Log Management Component repository.
2. Extract the contents of the `download.zip` file to a preferred location on your machine.

### Run the Application

1. Open a terminal window.
2. Navigate to the directory where you extracted the contents of the `download.zip` file.

```bash
cd path/to/extracted/folder
```

3. Run the following command to start the application using Docker:
```bash
docker-compose up -d
```
