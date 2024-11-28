async function getListRefCarType({groupNumber, parentTypeId}){

    const result = await axios.get(`/reference/listRefCarType`, {params: {groupNumber, parentTypeId}})

    console.log(result.data)
    return result.data
}
