import React from 'react';
import { Routes, Route } from 'react-router-dom';
import PrivateRoute from './PrivateRoute';
import PublicRoute from './PublicRoute';
// import { Components } from 'views';
import { routes } from './routes';


const Navigator = () => {
  return (
    <>
      <Routes>
        {/*Components component only for development purposes only*/}
        {/* <Route path="/components" element={<Components />} /> */}
        {routes.map(route => (
          <Route
            key={route.name}
            path={route.path}
            element={
              route.protected ? (
                <PrivateRoute pageName={route.name} /* roles={route.roles} */>
                  {route.element}
                </PrivateRoute>
              ) : (
                <PublicRoute>{route.element}</PublicRoute>
              )
            }
          />
        ))}
        <Route path="*" element={<div>Not found</div>} />
      </Routes>
    </>
  );
};

export default Navigator;
