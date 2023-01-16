
function TodoComponent(){

    const todayDate = new Date()
    const tragetDate = new Date(todayDate.getFullYear()+2, todayDate.getMonth(), todayDate.getDay())
    const todos = [
      {id: 1 , description:'Leanr Full Stack Development', isDone:false, tragetDate:tragetDate},
      {id: 2 , description:'Leanr Android Development', isDone:false, tragetDate:tragetDate},
      {id: 3 , description:'Leanr AWS', isDone:false, tragetDate:tragetDate}
    ]
    return(
        <div className='container'>
              <h1>Todo List </h1> 
                <div>
                  <table className='table'>
                    <thead>
                      <tr>
                      <td> ID </td>
                      <td> Description </td>
                      <td> isDone? </td>
                      <td> Traget Date </td>
                      </tr>
                    </thead>
                    <tbody>
                      {
                        todos.map(
                          todo =>(
                            <tr key={todo.id}>
                            <td>{todo.id}</td>
                            <td> {todo.description}</td>
                            <td>{todo.isDone.toString()}</td>
                            <td>{todo.tragetDate.toDateString()}</td>
                            </tr>
                          )
                        )
                      }
  
                    </tbody>
                  </table>
                </div>
        </div>
    )
  }
  
      // function AuthenticationCheck(){
      //   if(showSuccess)
      //     return <div>Authentication Successful </div>
      //     else if (showFailed)      
      //       return <div>Authentication Failed </div>
      //   return null
      // }

export default TodoComponent;