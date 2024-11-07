async function uploadToServer(formObj) {

    console.log("upload to Server....................")

    const response = await axios({
        method: 'post',
        url: '/upload',
        data: formObj,
        header:{'Content-Type' : 'multipart/form-data'}
    });

    return response.data
}

async function removeFileToServer(uuid, fileName, fileType){
    const response = await axios.delete(`/remove/${fileType}/${uuid}_${fileName}`)

    return response.data
}