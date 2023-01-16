import { useState } from 'react';
import { useNavigate} from 'react-router-dom';
import { useAuth } from './security/AuthContext';

function SignInComponent() {

    const authContext = useAuth()
    const [username, setUsername] = useState("aswagh")
    function handleUsername(event){
      setUsername(event.target.value)
    }
  
    const [password, setPassword] = useState("abcd")
    function handlePassword(event){
      setPassword(event.target.value)
    }
  
    // const [showSuccess, setSuccess] = useState(null)
    const [showFailed,setFailed] = useState(null)
  
    const navigate = useNavigate()
  
  
    function handleSubmit(){
        if(authContext.signIn(username,password)){
          navigate(`/welcome/${username}`)
        }
        else{
          setFailed(true)
        }
    }
  
    return(
      <div className='SignIn'>
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
  
  export default SignInComponent;