import { useState } from 'react';
import { BrowserRouter, Routes, Route, useNavigate, useParams, Link} from 'react-router-dom';
import './App.css';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <HeaderComponent />
        <Routes>
        <Route path='/' element={<SignInComponent />}> </Route>
        <Route path='/signin' element={<SignInComponent />}> </Route>
        <Route path='/welcome/:username' element={<WelcomeComponent />}> </Route>
        <Route path='/todos' element={<TodoComponent />}> </Route>
        <Route path='/logout' element={<LogoutComponent />}> </Route>
        <Route path='*' element={<ErrorComponent />}> </Route>
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

function SignInComponent() {

  const [username, setUsername] = useState("aswagh")
  function handleUsername(event){
    setUsername(event.target.value)
  }

  const [password, setPassword] = useState("abcd")
  function handlePassword(event){
    setPassword(event.target.value)
  }

  const [showSuccess, setSuccess] = useState(null)
  const [showFailed,setFailed] = useState(null)

  const navigate = useNavigate()


  function handleSubmit(){
      if(username==='aswagh' && password==='atul'){
        setSuccess(true)
        setFailed(false)
        navigate(`/welcome/${username}`)
      }
      else{
        setSuccess(false)
        setFailed(true)
      }
  }

  return(
    <div className='SignIn'>
      {showSuccess && <div>Authentication Successful </div>}
      {showFailed && <div>Authentication Failed </div>}
      <div>
        <label>Username:</label>
        <input type="text" name='username' value={username} onChange={handleUsername}/>
      </div>
      <div>
        <label>Password:</label>
        <input type="password" name='password' value={password} onChange={handlePassword}  />
      </div>
      <div>
        <button type="submit" name="submit" onClick={handleSubmit}>SignIn</button>
      </div>
    </div>
  )
}

function WelcomeComponent() {
  const {username} = useParams()
  return(
    <div>
      <div><h1>Welcome {username}!</h1></div>
      <div><h5>To access your todos <Link to='/todos'>Go here</Link> </h5></div>

    </div>
  )
}

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

function HeaderComponent(){
  return(
    <div>
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
          <div className="container-fluid">
            <a className="navbar-brand" href="#">Todo-App</a>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item">
                  <Link className="nav-link active" aria-current="page" to="/welcome">Home</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/todos">Todos List</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/signin">SignIn</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/logout">LogOut</Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
    </div>
  )
}
function FooterComponent(){
  return(
    <div className="container">
    <footer className="Foooter d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
      <div className="col-md-4 d-flex align-items-center">
        <a href="/" className="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
        </a>
        <span className="mb-3 mb-md-0 ">&copy; 2023 aswagh</span>
      </div>
  
    </footer>
  </div>
  )
}
function LogoutComponent(){
  return(
    <div className='Footer"'>
      <h1>You're logged out</h1>
      <h3>Thanks for visiting us! Have Nice Day 😊</h3>
    </div>
  )
}



function ErrorComponent(){
  return(
    <div>
      <h1> 404 Not Found !</h1>
      <h2> Sorry, This page isn't published</h2>
      <h3> In case of any queries! Please contact excompany@example.com</h3>
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

export default App;
