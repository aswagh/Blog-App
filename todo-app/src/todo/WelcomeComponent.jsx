import { useState } from 'react';
import { useParams, Link} from 'react-router-dom';



function WelcomeComponent() {
    const {username} = useParams()
    return(
      <div>
        <div><h1>Welcome {username}!</h1></div>
        <div><h5>To access your todos <Link to='/todos'>Go here</Link> </h5></div>
  
      </div>
    )
  }
  
  export default WelcomeComponent;