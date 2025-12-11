import React, { useState } from 'react';
import Register from './components/Register';
import Login from './components/Login';
import UploadExcel from './components/UploadExcel';
import ColumnSelector from './components/ColumnSelector';
import ChartViewer from './components/ChartViewer';
import MyUploads from './components/MyUploads';

export default function App(){
  const [logged,setLogged]=useState(localStorage.getItem('logged')==='true');
  const [selectedUpload,setSelectedUpload]=useState(null);
  const [selectedColumn,setSelectedColumn]=useState(null);

  return (
    <div className="app">
      <div className="sidebar">
        {!logged? <><Register/><hr/><Login onLogin={()=>setLogged(true)}/></>: <>
          <UploadExcel onUploaded={(id)=>{ setSelectedUpload(id); setSelectedColumn(null); }} />
          <hr/>
          <MyUploads onSelectUpload={(u)=>{ setSelectedUpload(u.id); setSelectedColumn(null); }} />
        </>}
      </div>
      <div className="main">
        {!logged? <div>Please log in to upload and view your files.</div> : <>
          <div style={{display:'flex',gap:8,alignItems:'center'}}>
            <div style={{fontWeight:700}}>Selected Upload:</div>
            <div>{selectedUpload || 'None'}</div>
            <div style={{marginLeft:'auto'}}><button className="btn" onClick={()=>{ localStorage.clear(); window.location.reload(); }}>Logout</button></div>
          </div>
          <div style={{marginTop:12}}>
            <ColumnSelector uploadId={selectedUpload} onSelect={setSelectedColumn} />
          </div>
          <div style={{marginTop:12}}>
            <ChartViewer column={selectedColumn} uploadId={selectedUpload} />
          </div>
        </>}
      </div>
    </div>
  );
}
