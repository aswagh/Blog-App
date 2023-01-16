import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import HeaderComponent from './HeaderComponent';
import FooterComponent from './FooterComponent';
import SignInComponent from './SigninComponent';
import ErrorComponent from './ErrorComponent';
import LogoutComponent from './LogoutComponent';
import WelcomeComponent from './WelcomeComponent';
import TodoComponent from './TodoComponent';
import AuthProvider, { useAuth } from './security/AuthContext';

function TodoApp() {

  function AuthenticationRoute({children}){
    const authContext = useAuth()

    if(authContext.isAuthenticated)
      return children
    
      return <Navigate to="/" />
  }

  return (
    <div className="TodoApp">
      <AuthProvider>
        <BrowserRouter>
        <HeaderComponent />
          <Routes>
          <Route path='/' element={<SignInComponent />}> </Route>
          <Route path='/signin' element={<SignInComponent />}> </Route>
          <Route path='/welcome/:username' element={
            <AuthenticationRoute>
              <WelcomeComponent />
            </AuthenticationRoute>
                }> 
            </Route>
          <Route path='/todos' element={
              <AuthenticationRoute>
                  <TodoComponent />
              </AuthenticationRoute>
               }> 
            </Route>
          <Route path='/logout' element={
              <AuthenticationRoute>
                <LogoutComponent />
                </AuthenticationRoute>
                }> 
            </Route>
          <Route path='*' element={<ErrorComponent />}> </Route>
          </Routes>
          <FooterComponent />
        </BrowserRouter>

      </AuthProvider>
    </div>
  );
}

export default TodoApp;
