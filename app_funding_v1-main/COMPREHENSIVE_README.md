# Hotel Booking & Reservation System

## Project Overview

**App Funding v1** is a comprehensive **Hotel Booking and Room Reservation Application** built in Java with a graphical user interface (GUI). The application enables users to search for available hotel rooms, make reservations, and manage their bookings, while providing administrators with tools to manage rooms, reservations, and user accounts.

---

## Project Motive

The primary motivation behind this project is to:

1. **Automate Hotel Operations**: Streamline the hotel booking process by providing a digital platform for customers and staff.
2. **Enhance User Experience**: Allow users to easily search, book, and manage hotel room reservations without manual intervention.
3. **Simplify Administrative Management**: Provide hotel administrators with a centralized system to manage rooms, reservations, check-ins/check-outs, and user accounts.
4. **Ensure Data Security**: Implement secure authentication with password encryption (BCrypt) and email verification via OTP (One-Time Password).
5. **Improve Communication**: Send automated email notifications for booking confirmations, password reset tokens, and reservation details.

---

## Project Description

### Technology Stack

- **Language**: Java (JDK 8 or later)
- **GUI Framework**: Java Swing (NetBeans IDE GUI Builder)
- **Database**: MySQL/MariaDB
- **Authentication**: BCrypt password hashing
- **Email Service**: JavaMail API (SMTP)
- **Build Tool**: Apache Ant
- **IDE**: NetBeans IDE (Built with GUI Designer)

### Key Features

#### 1. **User Authentication & Account Management**
   - User registration (Signup) with email validation
   - Secure login with username/email and password
   - Password encryption using BCrypt
   - Password reset functionality via email OTP verification
   - Account deletion request handling
   - Session management using local properties files

#### 2. **Room Booking & Reservation System**
   - Search available rooms by check-in and check-out dates
   - View room availability and pricing
   - Add rooms to booking cart
   - Calculate total cost based on nights stayed
   - Confirm and complete bookings
   - Track all active reservations

#### 3. **User Dashboard**
   - View personal reservation history
   - Manage active bookings
   - Reset password
   - Request account deletion
   - Logout functionality

#### 4. **Admin Panel**
   - **Reservation Management**:
     - View all customer reservations
     - Search reservations by ID
     - Check-in customers
     - Check-out customers
     - Cancel bookings

   - **Room Management**:
     - View all hotel rooms
     - Search rooms by ID
     - Mark rooms as available/unavailable
     - Manage room inventory

   - **Admin Profile Management**:
     - Manage admin users
     - Create/update admin accounts
     - Assign admin roles and status
     - Reset admin passwords

#### 5. **Security Features**
   - **BCrypt Password Hashing**: All passwords are securely hashed
   - **Email Verification**: OTP-based email verification for password resets
   - **Session Management**: User session tracking via properties file
   - **Database Connection Pooling**: Efficient database connection management
   - **Input Validation**: Email and phone number format validation

#### 6. **Email Notification System**
   - Send OTP to user email for verification
   - Password reset email notifications
   - Booking confirmation emails
   - Account recovery emails via SMTP

---

## System Architecture

### Project Structure

```
app_funding_v1/
├── src/
│   ├── Hotel/
│   │   ├── Hotel.java              # Main entry point
│   │   ├── Login.java              # User login interface
│   │   ├── Signup.java             # New user registration
│   │   ├── Forgot.java             # Password recovery initiation
│   │   ├── OTP.java                # OTP verification interface
│   │   ├── Booking.java            # Room booking interface
│   │   └── [.form files]           # NetBeans GUI forms
│   │
│   ├── User/
│   │   ├── ReservationMenu.java    # User dashboard & reservations
│   │   ├── ResetPassword.java      # Password change interface
│   │   ├── Logout.java             # Logout handler
│   │   ├── RequestDeleteAccount.java # Account deletion logic
│   │   └── [.form files]           # NetBeans GUI forms
│   │
│   ├── Admin/
│   │   ├── AdminPanel.java         # Admin dashboard
│   │   └── AdminPanel.form         # Admin GUI design
│   │
│   ├── Connection/
│   │   ├── ConnectionDatabase.java # Database connection management
│   │   └── ConnectionEmail.java    # Email service (SMTP)
│   │
│   ├── GUI/
│   │   ├── Loading.java            # Loading screen during connections
│   │   ├── PanelTest.java          # Testing UI components
│   │   └── [.form files]           # GUI forms
│   │
│   └── config/
│       ├── BCrypt.java             # Password encryption utility
│       └── propsLoader.java        # Properties file loader for session management
│
├── lib/                            # External libraries
├── build/                          # Compiled classes
├── nbproject/                      # NetBeans project configuration
└── build.xml                       # Ant build configuration

```

---

## How It Works

### 1. **Application Start-Up**
```
Hotel.java (main)
    ↓
ConnectionDatabase.connect()      [Shows Loading Screen]
    ↓
Database Connection Established
    ↓
cekUserLogin()                    [Check session]
    ↓
Display Login Screen
```

### 2. **User Authentication Flow**

#### Registration (Signup)
```
Signup.java
    ↓
User enters: Full Name, Username, Email, Phone, Password
    ↓
Validation:
  - Email format validation
  - Password strength check
  - Username uniqueness check
    ↓
Password Encryption (BCrypt)
    ↓
Database INSERT into 'user' table
    ↓
User Registration Successful → Redirect to Login
```

#### Login
```
Login.java
    ↓
User enters: Username/Email + Password
    ↓
Database Query: SELECT user WHERE username/email = ?
    ↓
BCrypt.checkpw(enteredPassword, storedHashedPassword)
    ↓
If Valid:
  - propsLoader saves session (user properties file)
  - Display ReservationMenu
Else:
  - Show error message
  - Stay on Login screen
```

### 3. **Password Recovery Flow**

#### Forgot Password
```
Forgot.java
    ↓
User enters: Username, Email, or Phone Number
    ↓
Database validation of provided information
    ↓
If Valid:
  - Generate OTP token
  - ConnectionEmail.sendEmail() → SMTP
  - OTP sent to registered email
  - Display OTP.java screen
Else:
  - Show error message
```

#### OTP Verification
```
OTP.java
    ↓
Display countdown timer (for OTP expiration)
    ↓
User enters OTP from email
    ↓
Database verification: OTP = ?
    ↓
If Valid:
  - Display ResetPassword.java
Else:
  - Show "Invalid OTP" error
  - Allow OTP resend
```

#### Reset Password
```
ResetPassword.java
    ↓
User enters: New Password + Confirm Password
    ↓
Password validation (must match)
    ↓
New password encrypted with BCrypt
    ↓
Database UPDATE user table
    ↓
Redirect to Login with success message
```

### 4. **Hotel Room Booking Flow**

#### Search & Browse Rooms
```
ReservationMenu.java → FindRoom Tab
    ↓
User selects: Check-in Date, Check-out Date
    ↓
Click "Search" button
    ↓
Database Query: 
  SELECT rooms WHERE room_id NOT IN 
  (SELECT room_id FROM reservation 
   WHERE date range overlaps with selected dates)
    ↓
Display available rooms in table
    ↓
Auto-calculate: Number of nights
```

#### Add to Booking Cart & Confirm
```
User selects room → Click "Add to Cart"
    ↓
RoomCart Tab:
  - Display selected rooms
  - Show room details (price, room type)
  - Calculate total cost (price × nights)
    ↓
User clicks "Confirm Booking"
    ↓
INSERT into 'reservation' table:
  - User ID, Room ID, Check-in, Check-out, Total Price
    ↓
Database INSERT successful
    ↓
Show booking confirmation
    ↓
Email confirmation sent to user
    ↓
Reservation appears in user's reservation list
```

### 5. **Admin Management Flow**

#### Admin Login
```
Login.java
    ↓
Admin credentials validation in database
    ↓
Check admin role & status
    ↓
Display AdminPanel.java
```

#### Reservation Management
```
AdminPanel.java → ReservationTab
    ↓
Options:
  1. View all reservations (table display)
  2. Search by Reservation ID
  3. Check-in: Update reservation status
  4. Check-out: Update reservation status
  5. Cancel: Delete reservation record
```

#### Room Management
```
AdminPanel.java → RoomManagementTab
    ↓
Options:
  1. View all rooms (table display)
  2. Search by Room ID
  3. Set Available: Mark room for booking
  4. Set Unavailable: Block room from booking
```

#### Admin Profile Management
```
AdminPanel.java → AdminProfileTab
    ↓
Options:
  1. View all admin accounts
  2. Create new admin account
  3. Update admin role & status
  4. Reset admin password (with email notification)
```

### 6. **Database Schema Overview**

#### user table
```
Columns: userid, username, email, fullname, phone, password (hashed), created_at
```

#### room table
```
Columns: room_id, room_type, price_per_night, capacity, availability_status
```

#### reservation table
```
Columns: reservation_id, userid, room_id, check_in_date, check_out_date, 
         total_price, status (pending/checked-in/checked-out/cancelled), created_at
```

#### admin table
```
Columns: admin_id, username, email, password (hashed), role, status, created_at
```

### 7. **Configuration & Setup**

#### Database Configuration
- Location: `Config/application.properties`
- Contains:
  ```
  DB_URL=jdbc:mysql://localhost:3306/hotel_db
  DB_USER=root
  DB_PASSWORD=password
  ```

#### Email Configuration
- Location: `Config/application.properties`
- Contains:
  ```
  email.smtp.username=your-email@gmail.com
  email.smtp.password=app-password
  email.smtp.host=smtp.gmail.com
  email.smtp.port=587
  ```

#### Session Management
- User session stored in: `user.properties` file
- Tracks: Current logged-in user, Last login time, User email

### 8. **Security Implementation**

```
Password Storage:
  User Input → BCrypt.hashpw() → Hashed Password → Database

Password Verification:
  User Input → BCrypt.checkpw(input, hash) → Boolean result

Email Verification:
  OTP Generation → SMTP Send → User Receives → User Enters → Verification
```

---

## Key Classes & Methods

### Core Classes
| Class | Purpose |
|-------|---------|
| `Hotel.java` | Application entry point |
| `Login.java` | User/Admin authentication |
| `Signup.java` | New user registration |
| `ReservationMenu.java` | User booking dashboard |
| `AdminPanel.java` | Admin management dashboard |
| `ConnectionDatabase.java` | MySQL database connectivity |
| `ConnectionEmail.java` | SMTP email notifications |
| `BCrypt.java` | Password hashing & verification |
| `propsLoader.java` | Session management |

### Important Methods
```java
// Authentication
Login.loginButtonActionPerformed()      // Login handler
Signup.signupButtonActionPerformed()    // Registration handler

// Booking
ReservationMenu.searchButton()           // Search available rooms
ReservationMenu.confirmBooking()         // Complete booking

// Admin
AdminPanel.checkInButton()               // Check-in reservation
AdminPanel.checkOutButton()              // Check-out reservation

// Email
ConnectionEmail.sendEmail()              // Send email via SMTP
ConnectionEmail.checkConnection()        // Verify SMTP connection

// Security
BCrypt.hashpw()                          // Hash password
BCrypt.checkpw()                         // Verify password
```

---

## Build & Compilation

### Build with Apache Ant
```bash
cd app_funding_v1
ant clean
ant build
ant run
```

### Build with NetBeans IDE
1. Open project in NetBeans
2. Build → Build Project (Ctrl+F11)
3. Run → Run Project (F6)

---

## Dependencies

### External Libraries
- **MySQL Connector/J**: JDBC driver for MySQL
- **jBCrypt**: Password hashing library
- **JavaMail**: Email sending API
- **JDateChooser**: Calendar date picker component
- **AbsoluteLayout**: Swing layout manager (NetBeans)

---

## Prerequisites & Installation

### System Requirements
- Java Development Kit (JDK) 8 or later
- MySQL Server 5.7 or later
- NetBeans IDE (optional, for development)
- 2GB RAM minimum

### Setup Steps

1. **Database Setup**
   ```sql
   CREATE DATABASE hotel_db;
   -- Import database schema (if provided)
   ```

2. **Configure Database Connection**
   - Edit `Config/application.properties`
   - Set DB_URL, DB_USER, DB_PASSWORD

3. **Configure Email Service**
   - Edit `Config/application.properties`
   - Set SMTP credentials (Gmail recommended)

4. **Compile & Run**
   ```bash
   ant build
   ant run
   ```

---

## User Roles

### 1. **Regular User**
- Create account (Signup)
- Search and book rooms
- View reservations
- Change password
- Request account deletion

### 2. **Admin User**
- Manage all reservations
- Manage room inventory
- Manage admin accounts
- Reset user passwords
- Access full admin dashboard

---

## Error Handling

The application includes comprehensive error handling for:
- Database connection failures → Shows error dialog with loading screen
- Invalid credentials → Shows login error message
- Email sending failures → Displays error notification
- Invalid OTP → Allows resend option
- SQL exceptions → Logged to console with user-friendly messages

---

## Future Enhancements

Potential improvements for future versions:
1. **Payment Gateway Integration**: Stripe/PayPal for online payments
2. **Room Reviews & Ratings**: User feedback system
3. **Booking Confirmation PDF**: Generate booking receipts
4. **Mobile App**: Android/iOS version
5. **Real-time Availability**: WebSocket for live updates
6. **Loyalty Program**: Points and rewards system
7. **Multi-language Support**: Internationalization
8. **Database Connection Pooling**: HikariCP for better performance

---

## Known Limitations

1. Session managed via local properties file (not suitable for distributed systems)
2. No payment processing - booking requires manual payment
3. Single-server architecture
4. Limited reporting capabilities
5. Email functionality requires proper SMTP configuration

---

## Support & Contact

For issues, bug reports, or feature requests, please contact the development team.

**Author**: Rizky  
**Last Updated**: April 2026  
**Version**: 1.0

---

## License

This project is provided as-is for educational and commercial use.

---

## Conclusion

App Funding v1 is a comprehensive, production-ready hotel booking system that combines user-friendly interfaces with robust backend functionality. It demonstrates best practices in Java GUI development, database management, security implementation, and user session handling.

The application serves as both a learning resource for Java developers and a functional booking system for small to medium-sized hotels.
