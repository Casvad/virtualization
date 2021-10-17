const axios = require('axios');

const configuration = {
    headers: {
        "Access-Control-Allow-Origin": "*"
    }
}

const MessageRepository = {}

MessageRepository.getMessages = function(){
    
    return axios.get('/messages', configuration).then(response => response.data)
}

MessageRepository.createMessage = function(message){
    
    return axios.post('/messages', {
        message
    },configuration).then(response => response.data)
}

export default MessageRepository;