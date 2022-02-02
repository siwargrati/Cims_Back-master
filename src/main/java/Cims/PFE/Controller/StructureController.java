package Cims.PFE.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Cims.PFE.Dao.StructureRepository;
import Cims.PFE.Dao.PersonnelRepository;
import Cims.PFE.Entities.Structure;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Service.StructureService;
import Cims.PFE.payload.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StructureController {
@Autowired
private StructureService StructureService;

@Autowired
private PersonnelRepository personnelRepository;
@Autowired
private StructureRepository StructRepository;

@GetMapping(value="/listStructures")
public List<Structure> listStructures(){
return StructureService.listAll();
}

@PostMapping(value="/addStructure")
public ResponseEntity<?> save(@RequestBody Structure d) {

switch (d.getDirection()) {

case "Aucune" :{d.setDirection_a("Aucune");
  switch (d.getDivision()) {
      case "Aucune" :{d.setDivision_a("Aucune");d.setService_a("Aucune");}
      break;}}
                                         
break;

case "Direction générale" :{d.setDirection_a("إدارة عامة");
switch (d.getDivision()) {
    case "-" :{d.setDivision_a("-");d.setService_a("-");}
    break;
        case "unité de contrôle de gestion":{d.setDivision_a("وحدة التحكم في الإدارة");d.setService_a("-");}
    break;
    case "unité audit interne ":{d.setDivision_a("وحدة المراجعة الداخلية");
                                switch(d.getService()) {
                                   case "-":d.setService_a("-");
                                   break;
                                   case "service de budget ":d.setService_a("خدمة الميزانية");
                                   break;}}
   break;}}
break;


case "Département de coordiantion technique et de gestion":{
d.setDirection_a("قسم التنسيق الفني والإدارة");
switch (d.getDivision()) {
    case "-" : {d.setDivision_a("-");d.setService_a("-");}
    break;
   case "unité de fomrmation et marketing ":{d.setDivision_a("وحدة التدريب والتسويق");d.setService_a("-");}
   break;
   }}
break;

case "Direction des affaires administratives et financières":{d.setDirection_a("مديرية الشؤون الادارية والمالية");
         switch (d.getDivision()) {
                case "-" : {d.setDivision_a("-");d.setService_a("-");}
                break;
                
            
                
                case "division des affaires administratives" :{d.setDivision_a("قسم الشئون الادارية");
                                                              switch (d.getService()) {
                                                              case "-":d.setService_a("-");
                                                              break;
                                                              case "service des ressources humaines":d.setService_a("قسم الموارد البشرية");
                                                              break;
                                                              case "service des moyens communs":d.setService_a("المشترك يعني الخدمة");
                                                              break;
                                                              }}
                break;
                  case "division des affaires financières" :{d.setDivision_a("قسم الشؤون المالية");}
                                                             switch (d.getService()) {
                                                             case "-":d.setService_a("-");
                                                             break;
                                                             case "service des finances":d.setService_a("قسم المالية");
                                                             break;
                                                             case "service de comptabilité":d.setService_a("خدمة المحاسبة");
                                                                 break;
                                                             case "service des marchéss ": d.setService_a("قسم الاسواق");
                                                                 break;
                                                             }
                  break;}}
break;
case "Direction d\"exploitationn , assistance, de réseaux et sécurité":{d.setDirection_a("توجيه العمليات والمساعدة والشبكات والأمن");
          switch (d.getDivision()) {
               case "-" :{d.setDivision_a("-");d.setService_a("-");}
               break;
               case "division d\"exploitation et de gestion des sites" :{d.setDivision_a("قسم عمليات وإدارة الموقع");
                                                                          switch(d.getService()) {
                                                                          case "-":d.setService_a("-");
                                                                          break;
                                                                          case "service d\"exploitation":d.setService_a("قسم التشغيل");
                                                                          break;
                                                                          case "service de gestion des sites":d.setService_a("خدمة إدارة الموقع");
                                                                          break;
                                                                          }
               }
                break;
               case "division d\"assistance technique et maintenance" :{d.setDivision_a("قسم المساعدة الفنية والصيانة");
                                                                        switch (d.getService()) {
                                                                        case "service de maintenance ":  d.setService_a("خدمة الصيانة");
                                                                        break;
                                                                        case"-":d.setService_a("-");
                                                                        break;
                                                                        }}
                  break;
                   case "division de réseaux et sécurité" :{d.setDivision_a("قسم الشبكات والأمن");
                                                             switch (d.getService()) {
                                                             case"-":d.setService_a("-");
                                                             break;
                                                             case"service de réseaux et sécurité ":d.setService_a("خدمة الشبكة والأمن");
                                                             break;
                                                             }}
               break;}}
break;
case "Direction d\"études et développement informatique":{d.setDirection_a("إدارة الدراسات وتطوير تكنولوجيا المعلومات");
      switch (d.getDivision()) {
                        case "-" :{d.setDivision_a("-");d.setService_a("-");}
                        break;
                case "divison des études " :{d.setDivision_a("قسم الدراسات");
                                              switch (d.getService()) {
                                              case"-":d.setService_a("-");
                                              break;
                                              case"service des études":d.setService_a("قسم الدراسات");
                                              break;
                                              case"service d\"architecture du S.I":d.setService_a("خدمة هندسة نظم المعلومات");
                                              }
                }
                break;
               case "division de developpement" :{d.setDivision_a("قسم التنمية");
                                                   switch (d.getService()) {
                                                   case"-":d.setService_a("-");
                                                   break;
                                                   case"service developpement du S.I ":d.setService_a("قسم تطوير نظم المعلومات");
                                                   break;
                                                   case "service maintenance et documentation du S.I ":d.setService_a("خدمة صيانة وتوثيق نظم المعلومات ");
                                                   break;
                                                   }
               }
               break;}}
break;
}

List<Structure> list=StructRepository.getStruct(d.getDirection(),d.getDirection_a(),d.getDivision(),d.getDivision_a(), d.getService(),d.getService_a());
if(list.isEmpty()) {
StructureService.save(d);
return ResponseEntity.ok(new MessageResponse("Département ajouté"));
}else return ResponseEntity.badRequest().body(new MessageResponse("Département existe déja !!!"));

}

@PutMapping(value="/updateStructure/{id}")
public  ResponseEntity<?> update(@PathVariable(name="id") Long id,@RequestBody Structure d){
switch (d.getDirection()) {
case "Direction générale" :{d.setDirection_a("إدارة عامة");
  switch (d.getDivision()) {
      case "-" :{d.setDivision_a("-");d.setService_a("-");}
      break;
          case "unité de contrôle de gestion":{d.setDivision_a("وحدة التحكم في الإدارة");d.setService_a("-");}
      break;
      case "unité audit interne ":{d.setDivision_a("وحدة المراجعة الداخلية");
                                  switch(d.getService()) {
                                     case "-":d.setService_a("-");
                                     break;
                                     case "service de budget ":d.setService_a("خدمة الميزانية");
                                     break;}}
     break;}}
break;
case "Département de coordiantion technique et de gestion":{
d.setDirection_a("قسم التنسيق الفني والإدارة");
switch (d.getDivision()) {
    case "-" : {d.setDivision_a("-");d.setService_a("-");}
    break;
   case "unité de fomrmation et marketing ":{d.setDivision_a("وحدة التدريب والتسويق");d.setService_a("-");}
   break;
   }}
break;

case "Direction des affaires administratives et financières":{d.setDirection_a("مديرية الشؤون الادارية والمالية");
         switch (d.getDivision()) {
                case "-" : {d.setDivision_a("-");d.setService_a("-");}
                break;
                case "division des affaires administratives" :{d.setDivision_a("قسم الشئون الادارية");
                                                              switch (d.getService()) {
                                                              case "-":d.setService_a("-");
                                                              break;
                                                              case "service des ressources humaines":d.setService_a("قسم الموارد البشرية");
                                                              break;
                                                              case "service des moyens communs":d.setService_a("المشترك يعني الخدمة");
                                                              break;
                                                              }}
                break;
                  case "division des affaires financières" :{d.setDivision_a("قسم الشؤون المالية");}
                                                             switch (d.getService()) {
                                                             case "-":d.setService_a("-");
                                                             break;
                                                             case "service des finances":d.setService_a("قسم المالية");
                                                             break;
                                                             case "service de comptabilité":d.setService_a("خدمة المحاسبة");
                                                               break;
                                                             case "service des marchéss ": d.setService_a("قسم الاسواق");
                                                                 break;
                                                             }
                  break;}}
break;
case "Direction d\"exploitationn , assistance, de réseaux et sécurité":{d.setDirection_a("توجيه العمليات والمساعدة والشبكات والأمن");
          switch (d.getDivision()) {
               case "-" :{d.setDivision_a("-");d.setService_a("-");}
               break;
               case "division d\"exploitation et de gestion des sites" :{d.setDivision_a("قسم عمليات وإدارة الموقع");
                                                                          switch(d.getService()) {
                                                                          case "-":d.setService_a("-");
                                                                          break;
                                                                          case "service d\"exploitation":d.setService_a("قسم التشغيل");
                                                                          break;
                                                                          case "service de gestion des sites":d.setService_a("خدمة إدارة الموقع");
                                                                          break;
                                                                          }
               }
                break;
               case "division d\"assistance technique et maintenance" :{d.setDivision_a("قسم المساعدة الفنية والصيانة");
                                                                        switch (d.getService()) {
                                                                        case "service de maintenance ":  d.setService_a("خدمة الصيانة");
                                                                        break;
                                                                        case"-":d.setService_a("-");
                                                                        break;
                                                                        }}
                  break;
                   case "division de réseaux et sécurité" :{d.setDivision_a("قسم الشبكات والأمن");
                                                             switch (d.getService()) {
                                                             case"-":d.setService_a("-");
                                                             break;
                                                             case"service de réseaux et sécurité ":d.setService_a("خدمة الشبكة والأمن");
                                                             break;
                                                             }}
               break;}}
break;
case "Direction d\"études et développement informatique":{d.setDirection_a("إدارة الدراسات وتطوير تكنولوجيا المعلومات");
      switch (d.getDivision()) {
                       case "-" :{d.setDivision_a("-");d.setService_a("-");}
                       break;
                case "divison des études " :{d.setDivision_a("قسم الدراسات");
                                              switch (d.getService()) {
                                              case"-":d.setService_a("-");
                                              break;
                                              case"service des études":d.setService_a("قسم الدراسات");
                                              break;
                                              case"service d\"architecture du S.I":d.setService_a("خدمة هندسة نظم المعلومات");
                                              }
                }
                break;
               case "division de developpement" :{d.setDivision_a("قسم التنمية");
                                                   switch (d.getService()) {
                                                   case"-":d.setService_a("-");
                                                   break;
                                                   case"service developpement du S.I ":d.setService_a("قسم تطوير نظم المعلومات");
                                                   break;
                                                   case "service maintenance et documentation du S.I ":d.setService_a("خدمة صيانة وتوثيق نظم المعلومات ");
                                                   break;
                                                   }
               }
               break;}}
break;
}
Structure Struct=StructureService.getById(id);
List<Structure> list=StructRepository.getStruct(d.getDirection(),d.getDirection_a(),d.getDivision(),d.getDivision_a(), d.getService(),d.getService_a());
//if(list.isEmpty()) {
Struct.setDirection(d.getDirection());
Struct.setDirection_a(d.getDirection_a());
Struct.setDivision(d.getDivision());
Struct.setDivision_a(d.getDivision_a());
Struct.setService(d.getService());
Struct.setService_a(d.getService_a());
Structure updatedStructure=StructureService.save(Struct);
return ResponseEntity.ok(new MessageResponse("Structure modifié"));
//}else return ResponseEntity.badRequest().body(new MessageResponse("Structure existe déja !!!"));

}

@GetMapping(value="/getStructure/{id}")
public Structure get(@PathVariable(name="id") Long id) {
return StructureService.getById(id);
}

@DeleteMapping(value="/deleteStructure/{id}")
public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
List<Personnel> list =personnelRepository.getStruct(id);
if(list.isEmpty()) {
if(StructureService.delete(id)) {
return ResponseEntity.ok(new MessageResponse("Département supprimeé"));
}
}
else return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Vous devez supprimer les personnels avant de supprimer"));

return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Échec lors de suppression"));
}

}