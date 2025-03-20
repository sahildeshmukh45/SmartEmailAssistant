# Email Writer Project

This project consists of three main components:
- Frontend (React application)
- Backend (Spring Boot application)
- Chrome Extension

## Setup Instructions

### Backend Setup
1. Navigate to the `email-writer-backend/email-writer` directory
2. Copy `.env.example` to `.env` and fill in your environment variables:
   ```
   cp .env.example .env
   ```
3. Update the `.env` file with your actual Gemini API credentials
4. Run the Spring Boot application

### Frontend Setup
1. Navigate to the `email-writer-frontend` directory
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
3. Click "Load unpacked" and select the `email-writer-extension` directory

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