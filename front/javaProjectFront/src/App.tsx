import React from 'react'
import { Provider } from 'react-redux'
import { BrowserRouter } from 'react-router-dom'

import { Navigator } from './navigator'
import { ConfigProvider, theme } from 'antd'

const App = () => {
  return (
    <ConfigProvider
      theme={{
        algorithm: theme.defaultAlgorithm,
        token: {
          colorPrimary: '#3d7cef',
        },
      }}
    >
      <div className="App">
        <BrowserRouter>
          <Navigator />
        </BrowserRouter>
      </div>
    </ConfigProvider>
  )
}

export default App
