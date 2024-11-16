# *OAuth 2.0 project*

- This project automates API testing using **REST Assured**, focusing on authorization, data validation, and response verification.
- It includes test cases that cover scenarios such as token generation, endpoint access, and data comparisons.
- In this project, Json parsing is done by using pojo classes deserialisation concept.

---

## **Features**
- Automated access token request.
- Validation of API responses using token-based authorization.
- Extraction and verification of course details.
- Comparison of actual vs. expected data from the API.

---

## **Test Cases Covered**

### **Test Case 1: Get Access Token**
- **Objective:** Retrieve an access token from the authorization server.
- **Steps:**
  1. Send a request with valid credentials to the authorization server.
  2. Extract and store the access token from the response.
- **Validation:** Ensure the token is retrieved successfully.

---

### **Test Case 2: Access End-Point Using Token**
- **Objective:** Use the access token to access a protected API endpoint and validate data.
- **Steps:**
  1. Send a GET request with the token to the API.
  2. Extract the LinkedIn ID from the response.
- **Validation:** Verify the LinkedIn ID matches the expected value.

---

### **Test Case 3: Extract Course Titles and Prices**
- **Objective:** Extract and print the titles and prices of web automation courses.
- **Steps:**
  1. Fetch course details from the API.
  2. Parse the response to retrieve titles and prices.
  3. Print the results.
- **Validation:** Verify the data matches the API response.

---

### **Test Case 4: Validate Price for a Specific Course**
- **Objective:** Retrieve and validate the price for a specified course title.
- **Steps:**
  1. Search the API response for the target course title.
  2. Extract and print its price.
- **Validation:** Confirm the price matches the expected value.

---

### **Test Case 5: Compare Actual vs. Expected Course Titles**
- **Objective:** Compare the course titles from the API response with an expected list.
- **Steps:**
  1. Extract all course titles from the response.
  2. Compare with the predefined expected titles.
- **Validation:** Ensure the titles match.

---

### **Test Output**
- **The test execution will provide**

1. Confirmation of access token retrieval.
2. Validation status of LinkedIn ID.
3. List of web automation course titles and prices.
4. Price validation for specific courses.
5. Comparison results for actual vs. expected course titles.

---

### **Test Execution Report**

![TestNg report](https://github.com/rohitpunekar242/oauth-2.0-project/blob/master/Test_Result.png)

---
