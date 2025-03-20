# Smart Email Assistant

A Chrome extension that helps you write professional emails using AI. This project integrates with Gmail to provide instant, context-aware email responses.

## Features

- 🤖 AI-powered email response generation
- 📧 Seamless Gmail integration
- 🎯 Context-aware responses
- ⚡ One-click reply generation
- 🔒 Secure API handling
- 🎨 Modern, responsive UI

## Project Structure

This project consists of three main components:

- Frontend (React application)
- Backend (Spring Boot application)
- Chrome Extension

## Setup Instructions

### Backend Setup
1. Navigate to the `smart-email-assistant-backend/email-writer` directory
2. Copy `.env.example` to `.env` and fill in your environment variables:
   ```
   cp .env.example .env
   ```
3. Update the `.env` file with your actual Gemini API credentials
4. Run the Spring Boot application

### Frontend Setup
1. Navigate to the `smart-email-assistant-frontend` directory
2. Install dependencies:
   ```
   npm install
   ```
3. Start the development server:
   ```
   npm run dev
   ```

### Chrome Extension Setup
1. Open Chrome and go to `chrome://extensions/`
2. Enable "Developer mode"
3. Click "Load unpacked" and select the `smart-email-assistant-extension` directory

## Environment Variables

The following environment variables are required for the backend:

- `GEMINI_URL`: Your Gemini API URL
- `GEMINI_KEY`: Your Gemini API Key
- `SERVER_PORT`: Server port (default: 8080)

Make sure to never commit your actual `.env` file or any files containing sensitive information to version control.

## Development

- Frontend runs on `http://localhost:5173` by default
- Backend runs on `http://localhost:8080` by default
- The Chrome extension will work with Gmail once both frontend and backend are running

## Tech Stack

- Frontend: React, Vite, JavaScript
- Backend: Spring Boot, Java
- AI: Google Gemini API
- Version Control: Git & GitHub

## Contributing

Feel free to submit issues and enhancement requests!

## License

This project is licensed under the MIT License - see the LICENSE file for details. 