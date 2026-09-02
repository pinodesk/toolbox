# Contributing to Toolbox

Thank you for your interest in contributing to Toolbox! This document provides guidelines and instructions for contributing to the project.

## Code of Conduct

Be respectful, constructive, and inclusive. Treat others as you would like to be treated.

## How to Contribute

### Reporting Bugs

Before creating bug reports, please check the existing issues to avoid duplicates. When creating a bug report, include:

- A clear and descriptive title
- Steps to reproduce the issue
- Expected behavior vs. actual behavior
- Environment details (OS, Java version, etc.)
- Any relevant logs or screenshots

### Suggesting Enhancements

Enhancement suggestions are welcome! Please provide:

- A clear and descriptive title
- A detailed description of the proposed enhancement
- Explain why this enhancement would be useful
- Provide examples if applicable

### Pull Requests

1. Fork the repository
2. Create a branch for your feature or bugfix
3. Make your changes following the project's coding standards
4. Run tests and ensure they pass
5. Commit your changes with clear messages
6. Push to your fork and submit a pull request

### Development Setup

```bash
# Clone the repository
git clone https://github.com/pinodesk/toolbox.git
cd toolbox

# Build the project
./mvnw clean install

# Run tests
./mvnw test

# Run code quality checks
./mvnw spotless:check pmd:check
```

### Coding Standards

- Follow the existing code style (enforced by Spotless)
- Write tests for new functionality
- Keep commits focused and atomic
- Update documentation as needed

## License

By contributing, you agree that your contributions will be licensed under the MIT License.
