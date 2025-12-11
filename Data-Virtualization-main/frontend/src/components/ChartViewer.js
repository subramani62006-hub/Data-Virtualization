import React,{useEffect,useState} from 'react';
import API from '../api';
import {PieChart,Pie,Cell,Tooltip,BarChart,Bar,XAxis,YAxis,CartesianGrid,LineChart,Line,ResponsiveContainer} from 'recharts';
export default function ChartViewer({column,uploadId}){
  const [data,setData]=useState([]);
  useEffect(()=>{ if(!column) return setData([]); API.get('/chart/aggregate',{params:{column, uploadId}}).then(r=> setData((r.data||[]).map(d=>({name:String(d.key), value:Number(d.value)})))) },[column,uploadId]);
  if(!column) return <div className='small'>Select a column</div>;
  if(data.length===0) return <div className='small'>No data</div>;
  return <div><h3>Chart for {column}</h3><ResponsiveContainer width='100%' height={320}><PieChart><Pie data={data} dataKey='value' label>{data.map((d,i)=><Cell key={i} />)}</Pie><Tooltip/></PieChart></ResponsiveContainer></div>;
}
