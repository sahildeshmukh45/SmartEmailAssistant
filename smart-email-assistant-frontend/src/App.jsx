import { useState } from 'react'
import './App.css'
import { Typography, Container, Box, TextField, FormControl, InputLabel, Select, Menu, MenuItem, CircularProgress, Button } from '@mui/material';
import axios from 'axios';

function App() {
  const [emailContent, setEmailContent] = useState('');
  const [tone, setTone] = useState('');
  const [generatedReply, setGeneratedReply] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSubmit = async () => {
    setLoading(true);
    setError('');

    try{
      const response = await axios.post("http://localhost:8080/api/email/generate", {
        emailContent,
        tone
      });
      setGeneratedReply(typeof response.data === 'string' ? response.data : JSON.stringify(response.data));
    } catch (error) {
      setError('Failed to generate reply. Please try again.');
      console.error( error);
    } finally {
      setLoading(false)
    }
  };

  return (
    <>
    <Container maxWidth="md" sx={{py:4}}>
    <Typography variant='h4' component="h1" gutterBottom> 
        Email Reply Generator
     </Typography>

     <Box sx={{mx: 3}}>    {/* // mx means margin left and right */}
     <TextField 
      fullWidth
      multiline
      rows={6}
      variant='outlined'
      label="Original Email Content"
      value={emailContent ||''}
      onChange={(e)=> setEmailContent(e.target.value)}
      sx={{mb:2}} />            {/* // mb means margin bottom */}
      
       <FormControl fullWidth sx={{mb:2}}>

        <InputLabel>Select Tone</InputLabel>

        <Select
        value={tone || ""}
        label={"Tone(optional)"}
        onChange={(e)=> setTone(e.target.value)}>

        <MenuItem value="">None</MenuItem>
        <MenuItem value="professional">Professional</MenuItem>
        <MenuItem value="casual">Casual</MenuItem>
        <MenuItem value="friendly">Friendly</MenuItem>

        </Select>
       </FormControl>

      <Button variant='contained'
       onClick={handleSubmit} 
       disabled={!emailContent || loading}
       fullWidth>
         {loading ? <CircularProgress size={24}/> : "Generate Reply"}
       </Button>

    </Box>

      {error && (
        <Typography color="error" sx={{mb:2}}> 
        {error}
        </Typography> 
      )}

      {generatedReply && (
        <Box sx={{mt:2}}> {/* // mt means margin top */}
          <Typography variant='h6' component="h2" gutterBottom>
            Generated Reply
          </Typography>
          <TextField
          fullWidth
          multiline
          rows={6}
          variant='outlined'
          value={generatedReply || ''}
          InputProps={{
            readOnly: true,
          }}
          sx={{mt:2}}/>

      <Button 
         variant='contained'
         sx={{mt:2}}
         onClick={()=> navigator.clipboard.writeText(generatedReply)}>
         Copy to Clipboard
      </Button>
    </Box>
      )}

    </Container>
    </>
  )
}

export default App
