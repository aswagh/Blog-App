import { Link} from 'react-router-dom';
import { useAuth } from './security/AuthContext';


function HeaderComponent(){
  const authContext = useAuth()
  const isAuthenticated = authContext.isAuthenticated
  
  function logout(){
    authContext.logout()
  }

  return(
    <div>
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
          <div className="container-fluid">
            <h2 className="navbar-brand" >Todo-App</h2>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item">
                  {isAuthenticated
                    &&  
                    <Link className="nav-link active" aria-current="page" to="/welcome">Home</Link>
                  }
                </li>
                <li className="nav-item">
                  {isAuthenticated
                    &&  
                    <Link className="nav-link" to="/todos">Todos List</Link>
                  }
                </li>
                <li className="nav-item">
                  {!isAuthenticated
                   &&  
                   <Link className="nav-link" to="/signin">SignIn</Link>
                  }
                </li>
                <li className="nav-item">
                  {isAuthenticated
                   &&  
                   <Link className="nav-link" to="/logout" onClick={logout}>LogOut</Link>
                  }
                  
                </li>
              </ul>
            </div>
          </div>
        </nav>
    </div>
  )
}

export default HeaderComponent;