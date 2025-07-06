# Peso Wise - Spring Boot Finance Tracker Backend

A comprehensive Spring Boot backend server using Kotlin for a finance tracker mobile app with enterprise-grade security, encryption, and scalability features.

## 🚀 Features

### Core Features
- **JWT Authentication** with 6-month token expiration
- **Device ID Tracking** for multi-device support
- **Email Verification** system with expiration handling
- **Field-level Encryption** for sensitive data
- **Login Attempt Logging** with comprehensive security tracking
- **Multi-profile Configuration** (dev/prod environments)
- **Complete RESTful API** endpoints optimized for mobile

### Database & Migrations
- **MySQL** for production, **H2** for development
- **Flyway Migrations** with undo support (`./migrate up` and `./migrate down`)
- **24 Migration Files** (12 forward + 12 undo) for complete schema
- **12 Database Tables** with proper relationships and indexes

### Security & Architecture
- **Clean Architecture** with proper separation of concerns
- **Role-based Access Control** (admin/user)
- **Spring Security** configuration with public auth endpoints
- **Comprehensive Logging** with security event tracking
- **Docker Containerization** for production deployment

## 🏗️ Architecture

```
src/main/kotlin/com/enlinka/peso_wise/
├── config/             # Configuration classes
│   ├── EncryptionConfig.kt
│   ├── FlywayConfig.kt
│   ├── JwtConfig.kt
│   └── SecurityConfig.kt
├── controller/         # REST Controllers
│   ├── AuthController.kt
│   ├── HomeController.kt
│   ├── AnalyticsController.kt
│   ├── BudgetController.kt
│   ├── WalletController.kt
│   ├── TransactionController.kt
│   ├── GoalController.kt
│   ├── IncomeController.kt
│   ├── CategoryController.kt
│   ├── SubCategoryController.kt
│   ├── ProfileController.kt
│   └── SettingsController.kt
├── model/              # JPA Entities
│   ├── User.kt
│   ├── Role.kt
│   ├── EmailCode.kt
│   ├── Category.kt
│   ├── Transaction.kt
│   ├── Goal.kt
│   └── UserAccount.kt
├── repository/         # Data Access Layer
│   ├── UserRepository.kt
│   ├── EmailCodeRepository.kt
│   └── TransactionRepository.kt
├── security/           # Security Components
│   ├── JwtTokenProvider.kt
│   ├── JwtAuthenticationFilter.kt
│   └── LoginAttemptLogger.kt
└── service/            # Business Logic (Empty stubs)
    ├── AuthService.kt
    └── TransactionService.kt
```

## 📊 Database Schema

### Core Tables
- **users** - User accounts with device ID tracking
- **roles** - Role-based access control
- **email_code** - Email verification system
- **categories** - Expense categories
- **subcategory** - Category subdivisions
- **transaction** - Financial transactions
- **goal** - Savings goals
- **user_accounts** - Credit cards, bank accounts
- **budget** - Budget management with JSON subcategories
- **bills** - Recurring bills and installments
- **subscription** - User subscription management
- **subscription_transaction** - Subscription payments

## 🔌 API Endpoints

### Public Endpoints (No Authentication)
```
POST /api/v1/auth/register
POST /api/v1/auth/login
POST /api/v1/auth/verification
POST /api/v1/auth/resend-verification
```

### Protected Endpoints (JWT Required)
```
GET  /api/v1/home
GET  /api/v1/analytics
GET  /api/v1/budget
GET  /api/v1/wallet

GET|POST|PUT|DELETE /api/v1/transaction
GET|POST|PUT|DELETE /api/v1/goal
GET|POST|PUT|DELETE /api/v1/income
GET|POST|PUT|DELETE /api/v1/category
GET|POST|PUT|DELETE /api/v1/{category}/sub
GET|POST|PUT|DELETE /api/v1/profile
GET|POST|PUT|DELETE /api/v1/settings
GET|POST|PUT|DELETE /api/v1/wallet/card
```

## 🛠️ Getting Started

### Prerequisites
- Java 17+
- Kotlin 1.8+
- MySQL 8.0+ (for production)
- Docker (optional)

### Development Setup

1. **Clone and run:**
```bash
./gradlew bootRun
```

2. **Access H2 Console (dev mode):**
```
http://localhost:8080/h2-console
```

3. **Test API endpoints:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","password":"password123"}'
```

### Docker Deployment

1. **Start with Docker Compose:**
```bash
docker-compose up
```

2. **Access services:**
- **API:** http://localhost:8080
- **Database Admin:** http://localhost:8081 (Adminer)

### Database Migrations

```bash
# Apply all migrations
./migrate up

# Rollback last migration
./migrate down
```

## ⚙️ Configuration

### Environment Variables
```bash
# Database
DB_HOST=localhost
DB_PORT=3306
DB_NAME=peso_wise_db
DB_USERNAME=peso_wise_user
DB_PASSWORD=peso_wise_password

# Security
JWT_SECRET=your-jwt-secret-key
ENCRYPTION_SECRET=your-encryption-secret

# Email
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password
```

### Profiles
- **dev** - H2 database, debug logging
- **prod** - MySQL database, optimized logging

## 🔒 Security Features

- **JWT Tokens** with 6-month expiration
- **Password Encryption** with BCrypt
- **Field-level Encryption** for sensitive data
- **Login Attempt Logging** with IP tracking
- **CORS Configuration** for mobile apps
- **Rate Limiting** and security headers

## 🧪 Testing

```bash
# Run tests
./gradlew test

# Build application
./gradlew build

# Check dependencies
./gradlew dependencies
```

## 📝 Development Notes

- **Service Layer:** Empty implementations provided for custom business logic
- **Encryption:** Configured but implementation needed in services
- **Lombok Support:** Available via kapt for Kotlin
- **Flyway:** Supports both up and down migrations
- **Logging:** Comprehensive security and application event tracking

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the documentation
- Review the API endpoint examples

---

**Peso Wise Backend** - Enterprise-grade finance tracker API built with Spring Boot and Kotlin.
