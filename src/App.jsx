import React from 'react'

const App = () => {
  const getAltitude = ()=>{
    navigator.geolocation.getCurrentPosition((pos)=>{
      console.log(pos)
    })
  }
  getAltitude()
  return (
    <div>
      <h1>Altitude Meter</h1>
      <div>

      </div>

    </div>
  )
}

export default App