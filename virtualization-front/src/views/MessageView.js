
import React from 'react';

import '../App.css';
import PropTypes from "prop-types";
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import MessageRepository from '../repositories/MessageRepository';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { CardActionArea } from '@mui/material';
import Typography from '@mui/material/Typography';

export default class MessageView extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      messages: [],
      inputText: ""
    };
  }

  componentDidMount() {
    MessageRepository.getMessages().then(data => {
      this.setState({
        ...this.state,
        messages: data
      })
    })
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <Grid container spacing={4} style={{paddingTop:"10%"}}>
            <Grid item xs={1} />
            <Grid item xs={6}>
              <TextField
                id="filled-basic"
                label="Insertar mensaje"
                variant="outlined"
                value={this.state.inputText}
                fullWidth
                InputLabelProps={{
                  className: "App-text",

                }}
                size="small"
                onChange={(text) => this.setText(text.target.value)}
              />
            </Grid>
            <Grid item xs={4}>
              <Button
                variant="contained"
                fullWidth
                onClick={() => this.createText()}
              >Crear
              </Button>
            </Grid>
            <Grid item xs={1} />
          </Grid>
          {this.getMessages()}
        </header>
      </div>
    );
  }

  setText(inputText) {

    this.setState({
      ...this.state,
      inputText
    })
  }

  createText() {
    MessageRepository.createMessage(this.state.inputText).then(response => {
      MessageRepository.getMessages().then(data => {
        this.setState({
          ...this.state,
          messages: data,
          inputText: ""
        })
      })
    })
  }

  getMessages() {
    return (
      <Grid container spacing={1} className="cardList">
        {
          this.state.messages.map((message, index) => {
            return this.getMessage(message, index)
          })
        }
      </Grid>

    )
  }

  getMessage(messageData, index) {
    const { message, created_at } = messageData;
    return (
      <Grid item xs={12} key={index} className="messageGridElement" style={{ paddingLeft: "10%", paddingRight: "10%" }}>
        <Card>
          <CardActionArea style={{ backgroundColor: "#353b45", borderColor: "white" }}>
            <CardContent>
              <Grid container spacing={1}>
                <Grid item xs={2}>
                  <Typography variant="body2" color="white">
                    {created_at}
                  </Typography>
                </Grid>
                <Grid item xs={10}>
                  <Typography gutterBottom variant="h6" component="div" color="white">
                    {message}
                  </Typography>
                </Grid>
              </Grid>
            </CardContent>
          </CardActionArea>
        </Card>
      </Grid>
    )
  }
}