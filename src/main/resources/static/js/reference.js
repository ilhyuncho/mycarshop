async function getListRefCarType({groupIndex, parentMenuName}){

    const result = await axios.get(`/reference/listRefCarType`, {params: {groupIndex, parentMenuName}})

    //console.log(result.data)
    return result.data
}
