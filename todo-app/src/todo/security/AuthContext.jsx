import { createContext, useContext, useState } from "react";


// create auth context
export const AuthContext = createContext()

export const useAuth = ()=> useContext(AuthContext)

//make it sharable among another compoents 
function AuthProvider({children}){

    //put state for auth context
    const [isAuthenticated,setAuthenticated] = useState(false)

    function signIn(username,password){
        if(username==='aswagh' && password==='atul'){
          setAuthenticated(true)
          return true;
        }
        else{
          setAuthenticated(false)
          return false;
        }
    }
    function logout(){
        setAuthenticated(false)
      }
    
    return(
        <AuthContext.Provider value={{isAuthenticated,signIn,logout}}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthProvider;