grammar Minimesser;

program: message+
       ;

message: 'message' ID '{' field* '}'
       ;

field: ID ':' typ ';'
     ;

typ: 'int8'  | 'int16'  | 'int32'  | 'int64'
   | 'uint8' | 'uint16' | 'uint32' | 'uint64'
   ;

ID : [a-zA-Z][a-zA-Z0-9_-]*;
WS : [ \n\t\r]+ -> skip;