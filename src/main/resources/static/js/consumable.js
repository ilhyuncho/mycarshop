async function registerConsumable(formObj) {

    const response = await axios.post(`/consumable/register`, formObj)

    console.log(response)
    return response.data
}

async function modifyConsumable(formObj) {

    const response = await axios.post(`/consumable/modify`, formObj)

    console.log(response)
    return response.data
}

async function removeConsumable(consumableId) {

    const response = await axios.post(`/consumable/remove/${consumableId}`)

    console.log(response)
    return response.data
}