import React, { useEffect } from 'react'
import { Navigate, useLocation } from 'react-router-dom'
import { isLoggedIn } from '../utils'

interface Props {
  children: React.ReactNode
  pageName: string
}

const PrivateRoute = ({ children }: Props): JSX.Element => {
  let location = useLocation()

  if (!isLoggedIn()) {
    return <Navigate to="/" state={{ from: location }} />
  }
  return <>{children}</>
}

export default PrivateRoute
