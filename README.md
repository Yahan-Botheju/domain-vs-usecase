### 📋 Scenario: Banking System - Deduct Monthly Maintenance Fee

You need to implement a feature that deducts a **Monthly Maintenance Fee of Rs. 200** from a bank account and updates the account's status accordingly.

#### **Business Rules & Requirements:**

1. **`BankAccount` Entity Rules (Domain Logic):**

    * If the account is in **DORMANT** status, the maintenance fee must not be deducted (`AccountDormantException` must be thrown).
    * Rs. 200 must be deducted from the account balance.
    * After deducting the fee, if the balance becomes **less than 0 (negative)**, the account status must automatically change to **`OVERDRAWN`**. (If the balance is not negative, it should remain `ACTIVE`.)

2. **`DeductMaintenanceFeeUseCase` Workflow (Use Case Logic):**

    * Accept the `accountNumber` from the request.
    * Load the `BankAccount` entity from the database through the `AccountRepository` (if it does not exist, throw a `ResourceNotFoundException`).
    * Call the appropriate domain method on the entity to deduct the maintenance fee.
    * Save the updated entity back to the database.
    * If the process is successful, return a Result DTO (Response).

### ✍️ Required Implementation

You need to implement the following **two classes**:

1. **`BankAccount` Class**

    * Pure Domain Logic
    * Without Lombok
    * Preserve Encapsulation

2. **`DeductMaintenanceFeeUseCaseImpl` Class**

    * Clean Use Case
    * Required Dependencies
    * Appropriate Annotations
    * Correct Flow
